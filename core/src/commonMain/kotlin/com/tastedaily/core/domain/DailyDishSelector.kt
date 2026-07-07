package com.tastedaily.core.domain

import com.tastedaily.core.model.Dish
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn
import kotlinx.datetime.Clock

/**
 * Picks "today's dish" deterministically so that every user sees the same new dish on a given day,
 * and different dishes across consecutive days (as long as the catalog is large enough).
 *
 * A dish whose [Dish.publishedDate] matches the requested date always wins. Otherwise we pick from
 * the freely-schedulable dishes (publishedDate == null) using a stable day-since-epoch index.
 */
class DailyDishSelector(
    private val allDishes: List<Dish>,
    private val today: () -> LocalDate = { Clock.System.todayIn(kotlinx.datetime.TimeZone.currentSystemDefault()) },
) {
    init {
        require(allDishes.isNotEmpty()) { "Dish catalog must not be empty" }
    }

    fun dishFor(date: LocalDate): Dish {
        val formatted = date.toString() // kotlinx-datetime LocalDate.toString() = "yyyy-MM-dd"
        allDishes.firstOrNull { it.publishedDate == formatted }?.let { return it }

        val pool = allDishes.filter { it.publishedDate == null }.ifEmpty { allDishes }
        val daysSinceEpoch = daysBetween(EPOCH, date)
        val idx = floorMod(daysSinceEpoch.toLong(), pool.size.toLong()).toInt()
        return pool[idx]
    }

    fun todayDish(): Dish = dishFor(today())

    /**
     * The most recent dishes ending today. Index 0 is today, 1 is yesterday, etc.
     * Never returns more than [count] items, and never duplicates the same dish on consecutive
     * positions when the catalog is large enough.
     */
    fun recentDishes(count: Int): List<Dish> {
        require(count >= 0) { "count must be >= 0" }
        if (count == 0) return emptyList()
        val base = today()
        val result = LinkedHashMap<String, Dish>()
        var offset = 0
        while (result.size < count && offset < 365) {
            val date = base.minus(DatePeriod(days = offset))
            val dish = dishFor(date)
            if (dish.id !in result) result[dish.id] = dish
            offset++
        }
        return result.values.toList()
    }

    companion object {
        val EPOCH = LocalDate(2024, 1, 1)

        /**
         * 计算两个日期之间的天数差（end - start），使用纯整数算法避免平台差异。
         */
        private fun daysBetween(start: LocalDate, end: LocalDate): Int {
            // 将日期转换为自公元元年以来的天数（简化算法，适用于 Common 代码）
            return toJulianDay(end.year, end.monthNumber, end.dayOfMonth) -
                toJulianDay(start.year, start.monthNumber, start.dayOfMonth)
        }

        private fun toJulianDay(year: Int, month: Int, day: Int): Int {
            val a = (14 - month) / 12
            val y = year + 4800 - a
            val m = month + 12 * a - 3
            return day + (153 * m + 2) / 5 + 365 * y + y / 4 - y / 100 + y / 400 - 32045
        }

        private fun floorMod(x: Long, y: Long): Long {
            val mod = x % y
            return if ((mod xor y) < 0 && mod != 0L) mod + y else mod
        }
    }
}

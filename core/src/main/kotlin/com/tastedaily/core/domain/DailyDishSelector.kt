package com.tastedaily.core.domain

import com.tastedaily.core.model.Dish
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

/**
 * Picks "today's dish" deterministically so that every user sees the same new dish on a given day,
 * and different dishes across consecutive days (as long as the catalog is large enough).
 *
 * A dish whose [Dish.publishedDate] matches the requested date always wins. Otherwise we pick from
 * the freely-schedulable dishes (publishedDate == null) using a stable day-since-epoch index.
 */
class DailyDishSelector(
    private val allDishes: List<Dish>,
    private val today: () -> LocalDate = { LocalDate.now() },
) {
    init {
        require(allDishes.isNotEmpty()) { "Dish catalog must not be empty" }
    }

    fun dishFor(date: LocalDate): Dish {
        val formatted = DATE_FORMAT.format(date)
        allDishes.firstOrNull { it.publishedDate == formatted }?.let { return it }

        val pool = allDishes.filter { it.publishedDate == null }.ifEmpty { allDishes }
        val daysSinceEpoch = ChronoUnit.DAYS.between(EPOCH, date)
        val idx = floorMod(daysSinceEpoch, pool.size.toLong()).toInt()
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
        var offset = 0L
        while (result.size < count && offset < 365) {
            val dish = dishFor(base.minusDays(offset))
            if (dish.id !in result) result[dish.id] = dish
            offset++
        }
        return result.values.toList()
    }

    companion object {
        val DATE_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        private val EPOCH = LocalDate.of(2024, 1, 1)

        private fun floorMod(x: Long, y: Long): Long {
            val mod = x % y
            return if ((mod xor y) < 0 && mod != 0L) mod + y else mod
        }
    }
}

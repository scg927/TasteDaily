package com.tastedaily.core.domain

import com.tastedaily.core.model.Dish
import com.tastedaily.core.model.MealType
import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.todayIn

/**
 * 每日三餐的数据结构
 */
data class DailyMeals(
    val date: LocalDate,
    val breakfast: List<Dish>,
    val lunch: List<Dish>,
    val dinner: List<Dish>,
) {
    val allDishes: List<Dish> get() = breakfast + lunch + dinner
    val totalCostYuan: Double get() = allDishes.sumOf { it.totalCostYuan }
}

/**
 * Picks "today's dish" deterministically so that every user sees the same new dish on a given day,
 * and different dishes across consecutive days (as long as the catalog is large enough).
 */
class DailyDishSelector(
    private val allDishes: List<Dish>,
    private val today: () -> LocalDate = { Clock.System.todayIn(TimeZone.currentSystemDefault()) },
) {
    init {
        require(allDishes.isNotEmpty()) { "Dish catalog must not be empty" }
    }

    /** 旧接口：返回单道菜（向后兼容） */
    fun dishFor(date: LocalDate): Dish {
        val formatted = date.toString()
        allDishes.firstOrNull { it.publishedDate == formatted }?.let { return it }

        val pool = allDishes.filter { it.publishedDate == null }.ifEmpty { allDishes }
        val daysSinceEpoch = daysBetween(EPOCH, date)
        val idx = floorMod(daysSinceEpoch.toLong(), pool.size.toLong()).toInt()
        return pool[idx]
    }

    fun todayDish(): Dish = dishFor(today())

    /**
     * 新接口：返回某日的三餐安排，每餐 3-4 道菜
     */
    fun mealsForDate(date: LocalDate): DailyMeals {
        val breakfastDishes = pickDishesForMeal(date, MealType.BREAKFAST, 4)
        val lunchDishes = pickDishesForMeal(date, MealType.LUNCH, 4)
        val dinnerDishes = pickDishesForMeal(date, MealType.DINNER, 4)
        return DailyMeals(date, breakfastDishes, lunchDishes, dinnerDishes)
    }

    fun todayMeals(): DailyMeals = mealsForDate(today())

    /**
     * 为某餐选菜：从对应 mealType 的菜池中，基于日期确定性选取
     */
    private fun pickDishesForMeal(date: LocalDate, mealType: MealType, count: Int): List<Dish> {
        // 先找该餐类的菜，如果没有则从通用菜池补充
        val mealPool = allDishes.filter { it.mealType == mealType }
        val anyPool = allDishes.filter { it.mealType == MealType.ANY }
        val pool = if (mealPool.size >= count) mealPool else mealPool + anyPool

        val daysSinceEpoch = daysBetween(EPOCH, date)
        val result = mutableListOf<Dish>()
        val usedIds = mutableSetOf<String>()

        // 从日期确定起始偏移，然后连续取 count 道不重复的菜
        val startIdx = floorMod(daysSinceEpoch.toLong(), pool.size.toLong()).toInt()
        var i = 0
        while (result.size < count && i < pool.size * 2) {
            val idx = (startIdx + i) % pool.size
            val dish = pool[idx]
            if (dish.id !in usedIds) {
                result.add(dish)
                usedIds.add(dish.id)
            }
            i++
        }
        return result
    }

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

        private fun daysBetween(start: LocalDate, end: LocalDate): Int {
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

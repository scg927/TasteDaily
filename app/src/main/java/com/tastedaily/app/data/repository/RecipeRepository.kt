package com.tastedaily.app.data.repository

import com.tastedaily.core.domain.DailyDishSelector
import com.tastedaily.core.model.Dish
import com.tastedaily.core.model.VideoAsset

class RecipeRepository(
    private val selector: DailyDishSelector,
    private val catalog: List<Dish>,
) {
    fun todayDish(): Dish = selector.todayDish()

    fun recentDishes(count: Int): List<Dish> = selector.recentDishes(count)

    fun dishById(id: String): Dish? = catalog.firstOrNull { it.id == id }

    fun videoById(id: String): VideoAsset? = catalog.flatMap { it.videos }.firstOrNull { it.id == id }
}

package com.tastedaily.app.data

import android.content.Context
import com.tastedaily.app.data.repository.RecipeRepository
import com.tastedaily.core.domain.DailyDishSelector
import com.tastedaily.core.seed.DishCatalog

/** Tiny manual DI container so ViewModels can grab dependencies without a framework. */
object AppContainer {
    lateinit var repository: RecipeRepository
        private set

    fun init(@Suppress("UNUSED_PARAMETER") context: Context) {
        val selector = DailyDishSelector(DishCatalog.all)
        repository = RecipeRepository(selector, DishCatalog.all)
    }
}

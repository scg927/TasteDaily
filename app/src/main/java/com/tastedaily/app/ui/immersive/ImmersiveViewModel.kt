package com.tastedaily.app.ui.immersive

import androidx.lifecycle.ViewModel
import com.tastedaily.app.data.AppContainer
import com.tastedaily.app.data.repository.RecipeRepository
import com.tastedaily.core.model.Dish
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ImmersiveViewModel(
    private val repository: RecipeRepository = AppContainer.repository,
) : ViewModel() {

    var dish: Dish? by mutableStateOf<Dish?>(null)
        private set

    fun load(dishId: String) {
        dish = repository.dishById(dishId)
    }
}

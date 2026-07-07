package com.tastedaily.app.ui.shopping

import androidx.lifecycle.ViewModel
import com.tastedaily.app.data.AppContainer
import com.tastedaily.app.data.repository.RecipeRepository
import com.tastedaily.core.model.Dish
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ShoppingListUiState(
    val dish: Dish? = null,
)

class ShoppingListViewModel(
    private val repository: RecipeRepository = AppContainer.repository,
) : ViewModel() {

    private val _state = MutableStateFlow(ShoppingListUiState())
    val state: StateFlow<ShoppingListUiState> = _state.asStateFlow()

    fun load(dishId: String) {
        _state.value = ShoppingListUiState(dish = repository.dishById(dishId))
    }
}

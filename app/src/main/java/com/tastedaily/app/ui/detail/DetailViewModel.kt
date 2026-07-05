package com.tastedaily.app.ui.detail

import androidx.lifecycle.ViewModel
import com.tastedaily.app.data.AppContainer
import com.tastedaily.app.data.repository.RecipeRepository
import com.tastedaily.core.model.Dish
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class DetailUiState(
    val dish: Dish? = null,
)

class DetailViewModel(
    private val repository: RecipeRepository = AppContainer.repository,
) : ViewModel() {

    private val _state = MutableStateFlow(DetailUiState())
    val state: StateFlow<DetailUiState> = _state.asStateFlow()

    fun load(dishId: String) {
        _state.value = DetailUiState(dish = repository.dishById(dishId))
    }
}

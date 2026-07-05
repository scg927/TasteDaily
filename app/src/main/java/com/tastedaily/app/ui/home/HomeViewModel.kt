package com.tastedaily.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tastedaily.app.data.AppContainer
import com.tastedaily.app.data.repository.RecipeRepository
import com.tastedaily.core.model.Dish
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class HomeUiState(
    val today: Dish? = null,
    val recent: List<Dish> = emptyList(),
    val loading: Boolean = true,
)

class HomeViewModel(
    private val repository: RecipeRepository = AppContainer.repository,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state: StateFlow<HomeUiState> = _state.asStateFlow()

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            _state.value = HomeUiState(
                today = repository.todayDish(),
                recent = repository.recentDishes(8),
                loading = false,
            )
        }
    }
}

package com.tastedaily.app.ui.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tastedaily.app.data.AppContainer
import com.tastedaily.app.data.repository.RecipeRepository
import com.tastedaily.core.model.Dish
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth

data class CalendarUiState(
    val month: YearMonth = YearMonth.now(),
    val selectedDate: LocalDate = LocalDate.now(),
    val today: LocalDate = LocalDate.now(),
    val dishForSelected: Dish? = null,
    val loading: Boolean = true,
)

class CalendarViewModel(
    private val repository: RecipeRepository = AppContainer.repository,
) : ViewModel() {

    private val _state = MutableStateFlow(CalendarUiState())
    val state: StateFlow<CalendarUiState> = _state.asStateFlow()

    init {
        loadDishForDate(LocalDate.now())
    }

    fun selectDate(date: LocalDate) {
        loadDishForDate(date)
    }

    fun changeMonth(delta: Int) {
        val newMonth = _state.value.month.plusMonths(delta.toLong())
        _state.value = _state.value.copy(month = newMonth)
    }

    private fun loadDishForDate(date: LocalDate) {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                selectedDate = date,
                dishForSelected = repository.dishForDate(date),
                loading = false,
            )
        }
    }
}

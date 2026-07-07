package com.tastedaily.app.ui.meal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tastedaily.app.data.AppContainer
import com.tastedaily.app.data.repository.RecipeRepository
import com.tastedaily.core.domain.DailyMeals
import com.tastedaily.core.model.Dish
import com.tastedaily.core.model.MealType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

data class MealDetailUiState(
    val date: LocalDate? = null,
    val mealType: MealType = MealType.ANY,
    val dishes: List<Dish> = emptyList(),
    val totalCostYuan: Double = 0.0,
    val loading: Boolean = true,
)

class MealDetailViewModel(
    private val repository: RecipeRepository = AppContainer.repository,
) : ViewModel() {

    private val _state = MutableStateFlow(MealDetailUiState())
    val state: StateFlow<MealDetailUiState> = _state.asStateFlow()

    fun load(dateStr: String, mealTypeStr: String) {
        viewModelScope.launch {
            val date = LocalDate.parse(dateStr)
            val mealType = runCatching { MealType.valueOf(mealTypeStr) }.getOrDefault(MealType.ANY)
            val meals = repository.mealsForDate(date)
            val dishes = when (mealType) {
                MealType.BREAKFAST -> meals.breakfast
                MealType.LUNCH -> meals.lunch
                MealType.DINNER -> meals.dinner
                else -> meals.allDishes
            }
            _state.value = MealDetailUiState(
                date = date,
                mealType = mealType,
                dishes = dishes,
                totalCostYuan = dishes.sumOf { it.totalCostYuan },
                loading = false,
            )
        }
    }
}

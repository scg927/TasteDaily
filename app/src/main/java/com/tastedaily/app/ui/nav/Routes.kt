package com.tastedaily.app.ui.nav

object Routes {
    const val CALENDAR = "calendar"
    const val BROWSE = "browse"
    const val HOME = "home"
    const val DETAIL = "detail/{dishId}"
    const val IMMERSIVE = "immersive/{dishId}"
    const val VIDEO = "video/{videoId}"
    const val MEAL_DETAIL = "meal/{date}/{mealType}"
    const val SHOPPING_LIST = "shopping/{dishId}"

    fun detail(dishId: String) = "detail/$dishId"
    fun immersive(dishId: String) = "immersive/$dishId"
    fun video(videoId: String) = "video/$videoId"
    fun mealDetail(date: String, mealType: String) = "meal/$date/$mealType"
    fun shoppingList(dishId: String) = "shopping/$dishId"
}

package com.tastedaily.app.ui.nav

object Routes {
    const val HOME = "home"
    const val DETAIL = "detail/{dishId}"
    const val IMMERSIVE = "immersive/{dishId}"
    const val VIDEO = "video/{videoId}"

    fun detail(dishId: String) = "detail/$dishId"
    fun immersive(dishId: String) = "immersive/$dishId"
    fun video(videoId: String) = "video/$videoId"
}

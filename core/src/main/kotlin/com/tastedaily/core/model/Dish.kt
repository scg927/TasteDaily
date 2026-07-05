package com.tastedaily.core.model

data class Dish(
    val id: String,
    val name: String,
    val tagline: String,
    val cuisine: String,
    val coverImageUrl: String,
    val difficulty: Difficulty,
    val durationMinutes: Int,
    val calories: Int,
    val ingredients: List<Ingredient>,
    val description: String,
    val article: Article,
    val videos: List<VideoAsset>,
    val steps: List<CookingStep>,
    /** yyyy-MM-dd if the dish is pinned to a specific date, null if it can be freely scheduled. */
    val publishedDate: String? = null,
)

enum class Difficulty(val label: String) {
    EASY("简单"),
    MEDIUM("中等"),
    HARD("较难"),
}

data class Ingredient(val name: String, val amount: String)

data class Article(
    val title: String,
    val author: String,
    val sections: List<ArticleSection>,
)

data class ArticleSection(
    val heading: String,
    val body: String,
)

data class VideoAsset(
    val id: String,
    val title: String,
    val videoUrl: String,
    val posterImageUrl: String,
    val durationSeconds: Int,
)

data class CookingStep(
    val index: Int,
    val title: String,
    val instruction: String,
    val durationSeconds: Int,
    val imageUrl: String?,
    val videoUrl: String?,
)

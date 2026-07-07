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
    /** 餐类：早餐/中餐/晚餐/通用 */
    val mealType: MealType = MealType.ANY,
    /** 预估成本（元），若为 null 则自动从 ingredients 汇总 */
    val estimatedCostYuan: Double? = null,
) {
    /** 计算总成本：优先使用 estimatedCostYuan，否则从食材汇总 */
    val totalCostYuan: Double
        get() = estimatedCostYuan ?: ingredients.sumOf { it.estimatedPriceYuan }
}

enum class MealType(val label: String) {
    BREAKFAST("早餐"),
    LUNCH("中餐"),
    DINNER("晚餐"),
    ANY("通用"),
}

enum class Difficulty(val label: String) {
    EASY("简单"),
    MEDIUM("中等"),
    HARD("较难"),
}

data class Ingredient(
    val name: String,
    val amount: String,
    /** 预估单价（元），默认 0 */
    val estimatedPriceYuan: Double = 0.0,
)

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

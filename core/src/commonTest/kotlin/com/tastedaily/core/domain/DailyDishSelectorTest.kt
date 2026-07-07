package com.tastedaily.core.domain

import com.tastedaily.core.model.Dish
import com.tastedaily.core.seed.DishCatalog
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import kotlinx.datetime.DatePeriod
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class DailyDishSelectorTest {

    private val catalog = DishCatalog.all
    private val fixedToday = LocalDate(2026, 7, 2)

    private fun selector(today: LocalDate = fixedToday) =
        DailyDishSelector(catalog, today = { today })

    @Test
    fun `catalog is non empty and each dish has content`() {
        assertTrue(catalog.isNotEmpty())
        catalog.forEach { dish ->
            assertTrue(dish.id.isNotBlank(), "dish id must be set")
            assertTrue(dish.steps.isNotEmpty(), "${dish.name} should have steps")
            assertTrue(dish.ingredients.isNotEmpty(), "${dish.name} should have ingredients")
            assertTrue(dish.article.sections.isNotEmpty(), "${dish.name} should have article sections")
        }
    }

    @Test
    fun `today dish is stable for the same date`() {
        val s = selector()
        val first = s.todayDish()
        val second = s.todayDish()
        assertEquals(first.id, second.id)
    }

    @Test
    fun `consecutive days yield different dishes when catalog is large enough`() {
        val s = selector()
        val today = s.todayDish()
        val tomorrow = s.dishFor(fixedToday.plus(DatePeriod(days = 1)))
        assertNotEquals(today.id, tomorrow.id, "tomorrow should be a different dish")
    }

    @Test
    fun `pinned published date overrides scheduling`() {
        val pinnedDate = LocalDate(2026, 7, 2)
        val pinned = catalog.first().copy(publishedDate = "2026-07-02")
        val others = catalog.drop(1)
        val s = DailyDishSelector(listOf(pinned) + others, today = { pinnedDate })
        assertEquals(pinned.id, s.todayDish().id)
    }

    @Test
    fun `recent dishes starts with today and has no duplicates`() {
        val s = selector()
        val recent = s.recentDishes(4)
        assertEquals(4, recent.size)
        assertEquals(s.todayDish().id, recent.first().id)
        assertEquals(recent.map { it.id }.toSet().size, recent.size)
    }

    @Test
    fun `recent dishes with zero count returns empty`() {
        assertEquals(emptyList<Dish>(), selector().recentDishes(0))
    }

    @Test
    fun `selection wraps around across years without crashing`() {
        val s = selector(today = LocalDate(2030, 12, 30))
        val dish = s.todayDish()
        assertTrue(catalog.any { it.id == dish.id })
    }

    @Test
    fun `published date is null for catalog dishes so they can be scheduled`() {
        // The default catalog is freely schedulable (no pinned dates).
        assertTrue(catalog.all { it.publishedDate == null })
        // sanity: ensure we did not accidentally leave a pinned date
        assertNull(catalog.first().publishedDate)
    }
}

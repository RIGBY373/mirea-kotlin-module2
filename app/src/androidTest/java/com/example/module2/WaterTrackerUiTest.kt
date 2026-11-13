package com.example.module2

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.module2.tasks.WaterTracker
import com.example.module2.tasks.WaterTrackerTags
import com.example.module2.ui.theme.Module2Theme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/** Задание 9. UI-тесты трекера воды из задания 8. */
class WaterTrackerUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            Module2Theme {
                WaterTracker()
            }
        }
    }

    private fun addWater(times: Int) {
        repeat(times) { composeTestRule.onNodeWithText("+250 мл").performClick() }
    }

    private fun endDay() {
        composeTestRule.onNodeWithText("Завершить день").performClick()
    }

    private fun assertWater(expected: String) {
        composeTestRule.onNodeWithTag(WaterTrackerTags.WATER_COUNT).assertTextEquals(expected)
    }

    private fun assertStreak(days: Int) {
        composeTestRule.onNodeWithTag(WaterTrackerTags.DAYS_STREAK)
            .assertIsDisplayed()
            .assertTextEquals("Дней подряд от 1500 мл: $days")
    }

    // 1) Кнопка добавления жидкости есть на экране и прибавляет по 250 мл
    @Test
    fun addWaterButton_isDisplayed() {
        composeTestRule.onNodeWithText("+250 мл").assertIsDisplayed()
    }

    @Test
    fun addWaterButton_adds250ml() {
        assertWater("0 мл")
        addWater(1)
        assertWater("250 мл")
        addWater(2)
        assertWater("750 мл")
    }

    // 2) Кнопка «Завершить день» есть на экране и обнуляет счётчик
    @Test
    fun endDayButton_isDisplayed() {
        composeTestRule.onNodeWithText("Завершить день").assertIsDisplayed()
    }

    @Test
    fun endDayButton_resetsWaterCounter() {
        addWater(3)
        endDay()
        assertWater("0 мл")
    }

    // 3) Текст с количеством дней подряд с нормой от 1500 мл
    @Test
    fun streakText_isDisplayedWithZeroAtStart() {
        assertStreak(0)
    }

    @Test
    fun streakText_increasesWhenGoalReached() {
        addWater(6) // 1500 мл
        endDay()
        assertStreak(1)
        addWater(7) // 1750 мл
        endDay()
        assertStreak(2)
    }

    @Test
    fun streakText_resetsWhenGoalMissed() {
        addWater(6)
        endDay()
        assertStreak(1)
        addWater(5) // 1250 мл — норма не выполнена
        endDay()
        assertStreak(0)
    }
}

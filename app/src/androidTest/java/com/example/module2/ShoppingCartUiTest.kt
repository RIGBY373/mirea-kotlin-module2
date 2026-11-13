package com.example.module2

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.module2.tasks.CART_TOTAL_TAG
import com.example.module2.tasks.ShoppingCartScreen
import com.example.module2.ui.theme.Module2Theme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/** Проверка исправлений задания 10. Цена нового товара в тестах фиксирована — 50 руб. */
class ShoppingCartUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            Module2Theme {
                ShoppingCartScreen(nextPrice = { 50 })
            }
        }
    }

    private fun assertTotal(sum: Int) {
        composeTestRule.onNodeWithTag(CART_TOTAL_TAG)
            .assertTextEquals("Товаров на сумму: $sum рублей")
    }

    @Test
    fun totalIsCorrectAfterSeveralRecompositions() {
        assertTotal(306)
        composeTestRule.onNodeWithText("Добавить товар").performClick()
        assertTotal(356)
        composeTestRule.onNodeWithText("Добавить товар").performClick()
        assertTotal(406)
        composeTestRule.onNodeWithText("Товар #5 - 50 рублей").assertIsDisplayed()
    }

    @Test
    fun removeDeletesLastProduct() {
        composeTestRule.onNodeWithText("Удалить товар").performClick()
        composeTestRule.onNodeWithText("Товар #3 - 56 рублей").assertDoesNotExist()
        assertTotal(250)
    }

    @Test
    fun removeButtonHiddenWhenCartIsEmpty() {
        repeat(3) { composeTestRule.onNodeWithText("Удалить товар").performClick() }
        assertTotal(0)
        composeTestRule.onNodeWithText("Удалить товар").assertDoesNotExist()
        composeTestRule.onNodeWithText("Добавить товар").performClick()
        composeTestRule.onNodeWithText("Удалить товар").assertIsDisplayed()
    }
}

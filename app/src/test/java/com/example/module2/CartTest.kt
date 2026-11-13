package com.example.module2

import com.example.module2.tasks.Cart
import com.example.module2.tasks.Product
import com.example.module2.tasks.initialCart
import com.example.module2.tasks.totalSum
import com.example.module2.tasks.withNewProduct
import com.example.module2.tasks.withoutLastProduct
import org.junit.Assert.assertEquals
import org.junit.Test

class CartTest {

    @Test
    fun totalSum_isSumOfPrices() {
        assertEquals(306, initialCart.totalSum)
    }

    @Test
    fun totalSum_doesNotAccumulateOnRepeatedReads() {
        repeat(3) { initialCart.totalSum }
        assertEquals(306, initialCart.totalSum)
    }

    @Test
    fun addProduct_increasesSumAndUsesNextId() {
        val cart = initialCart.withNewProduct(price = 50)
        assertEquals(356, cart.totalSum)
        assertEquals(Product(3, "Товар #4", 50), cart.products.last())
    }

    @Test
    fun removeLastProduct_removesOnlyLast() {
        val cart = initialCart.withoutLastProduct()
        assertEquals(listOf("Товар #1", "Товар #2"), cart.products.map { it.name })
        assertEquals(250, cart.totalSum)
    }

    @Test
    fun idsStayUniqueAfterRemoval() {
        val cart = initialCart.withoutLastProduct().withoutLastProduct()
            .withNewProduct(10).withNewProduct(20)
        assertEquals(cart.products.size, cart.products.map { it.id }.toSet().size)
    }

    @Test
    fun removeFromEmptyCart_staysEmpty() {
        assertEquals(Cart(emptyList()), Cart(emptyList()).withoutLastProduct())
    }
}

package com.example.module2.tasks

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

data class Cart(
    val products: List<Product>,
)

data class Product(
    val id: Int,
    val name: String,
    val price: Int,
)

/** Сумма, начиная с которой доставка бесплатная (строго больше), руб. */
const val FREE_DELIVERY_THRESHOLD = 500

const val CART_TOTAL_TAG = "cart_total"

val initialCart = Cart(
    listOf(
        Product(0, "Товар #1", 100),
        Product(1, "Товар #2", 150),
        Product(2, "Товар #3", 56),
    )
)

/** Сумма вычисляется из списка товаров, а не накапливается в поле Activity. */
val Cart.totalSum: Int get() = products.sumOf { it.price }

/** Новый товар получает следующий свободный id, даже если до этого товары удалялись. */
fun Cart.withNewProduct(price: Int): Cart {
    val nextId = (products.maxOfOrNull { it.id } ?: -1) + 1
    return copy(products = products + Product(nextId, "Товар #${nextId + 1}", price))
}

fun Cart.withoutLastProduct(): Cart = copy(products = products.dropLast(1))

/** Сохранение корзины в Bundle, чтобы она не терялась при повороте экрана. */
val CartSaver = listSaver<Cart, Any>(
    save = { cart -> cart.products.flatMap { listOf(it.id, it.name, it.price) } },
    restore = { saved ->
        Cart(
            saved.chunked(3).map { (id, name, price) ->
                Product(id as Int, name as String, price as Int)
            }
        )
    },
)

/** Задание 10. Исправленный экран корзины. */
@Composable
fun ShoppingCartScreen(
    modifier: Modifier = Modifier,
    nextPrice: () -> Int = { Random.nextInt(0, 100) },
) {
    var cart by rememberSaveable(stateSaver = CartSaver) { mutableStateOf(initialCart) }
    val context = LocalContext.current

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
    ) {
        for (product in cart.products) {
            Text(text = product.name + " - " + product.price + " рублей")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Товаров на сумму: ${cart.totalSum} рублей",
            modifier = Modifier.testTag(CART_TOTAL_TAG),
        )
        AddProductSection {
            cart = cart.withNewProduct(nextPrice())
            // Toast — побочный эффект, поэтому он показывается в обработчике нажатия,
            // а не во время композиции
            if (cart.totalSum > FREE_DELIVERY_THRESHOLD) {
                Toast.makeText(context, "Доставка бесплатная!", Toast.LENGTH_SHORT).show()
            }
        }
        RemoveProductSection(productCount = cart.products.size) {
            cart = cart.withoutLastProduct()
        }
    }
}

@Composable
fun AddProductSection(
    onAdd: () -> Unit,
) {
    Button(onClick = onAdd) {
        Text(text = "Добавить товар")
    }
}

@Composable
fun RemoveProductSection(
    productCount: Int,
    onRemove: () -> Unit,
) {
    if (productCount > 0) {
        Button(onClick = onRemove) {
            Text(text = "Удалить товар")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShoppingCartScreenPreview() {
    ShoppingCartScreen()
}

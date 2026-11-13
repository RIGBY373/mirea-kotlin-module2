package com.example.module2

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.module2.tasks.CircleInCorner
import com.example.module2.tasks.ContactsScreen
import com.example.module2.tasks.Hello
import com.example.module2.tasks.InitialsHeader
import com.example.module2.tasks.PressMeButton
import com.example.module2.tasks.ShoppingCartScreen
import com.example.module2.tasks.StretchedPurpleCircle
import com.example.module2.tasks.TextStylesScreen
import com.example.module2.tasks.WaterTracker

/** Пункты меню: номер задания и его краткое название. */
private val menuItems = listOf(
    1 to "Приветствие Hello(name)",
    3 to "Оформление текста",
    4 to "Кнопка «Нажми на меня»",
    5 to "Контейнер с инициалами",
    6 to "Изображение круга",
    7 to "Контакты: Column и Row",
    8 to "Трекер воды",
    10 to "Корзина онлайн-магазина",
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Module2App(userName: String?, startTask: Int = 0) {
    // Номер открытого задания; 0 — меню. Сохраняется при повороте экрана.
    var currentTask by rememberSaveable { mutableIntStateOf(startTask) }
    BackHandler(enabled = currentTask != 0) { currentTask = 0 }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        if (currentTask == 0) stringResource(R.string.app_name)
                        else "Задание $currentTask"
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
            )
        },
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            when (currentTask) {
                1 -> Hello(name = userName)
                3 -> TextStylesScreen()
                4 -> PressMeButton()
                5 -> InitialsHeader(initials = "АБ")
                6 -> Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    CircleInCorner()
                    StretchedPurpleCircle()
                }
                7 -> ContactsScreen()
                8 -> WaterTracker()
                10 -> ShoppingCartScreen()
                else -> TaskMenu(onTaskSelected = { currentTask = it })
            }
        }
    }
}

@Composable
private fun TaskMenu(onTaskSelected: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = stringResource(R.string.menu_title),
            style = MaterialTheme.typography.titleLarge,
        )
        menuItems.forEach { (number, title) ->
            OutlinedButton(
                onClick = { onTaskSelected(number) },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Задание $number. $title")
            }
        }
    }
}

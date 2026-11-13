package com.example.module2.tasks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val primaryColor = Color(0xFF3F5E96)
private val secondaryColor = Color(0xFF545F71)
private val buttonTextColor = Color.White

/** Объём одной порции, мл. */
const val WATER_PORTION_ML = 250

/** Дневная норма, при которой день засчитывается в серию, мл. */
const val DAILY_GOAL_ML = 1500

/** Теги для поиска элементов в UI-тестах. */
object WaterTrackerTags {
    const val WATER_COUNT = "water_count"
    const val DAYS_STREAK = "days_streak"
}

/**
 * Новое значение счётчика дней после завершения дня:
 * норма выполнена — серия растёт, иначе обнуляется.
 */
fun nextStreak(waterCount: Int, currentStreak: Int): Int =
    if (waterCount >= DAILY_GOAL_ML) currentStreak + 1 else 0

/** Текст со счётчиком дней подряд с выполненной нормой. */
fun streakText(days: Int): String = "Дней подряд от $DAILY_GOAL_ML мл: $days"

/** Задание 8. Трекер выпитой воды с подсчётом дней подряд с выполненной нормой. */
@Composable
fun WaterTracker(modifier: Modifier = Modifier) {
    // Состояния переживают рекомпозицию (remember) и поворот экрана (Saveable)
    var waterCount by rememberSaveable { mutableIntStateOf(0) }
    var daysStreak by rememberSaveable { mutableIntStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Трекер воды",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = primaryColor,
        )
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = "$waterCount мл",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = secondaryColor,
            modifier = Modifier.testTag(WaterTrackerTags.WATER_COUNT),
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
            shape = RoundedCornerShape(4.dp),
            onClick = { waterCount += WATER_PORTION_ML },
        ) {
            Text(
                text = "+$WATER_PORTION_ML мл",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = buttonTextColor,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = secondaryColor),
            shape = RoundedCornerShape(4.dp),
            onClick = {
                daysStreak = nextStreak(waterCount, daysStreak)
                waterCount = 0
            },
        ) {
            Text(
                text = "Завершить день",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = buttonTextColor,
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = streakText(daysStreak),
            fontSize = 20.sp,
            color = secondaryColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.testTag(WaterTrackerTags.DAYS_STREAK),
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WaterTrackerPreview() {
    WaterTracker()
}

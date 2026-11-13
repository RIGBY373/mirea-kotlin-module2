package com.example.module2.tasks

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.module2.R

/** Задание 6.1. Круг исходного размера в правом верхнем углу чёрного блока 240×120 dp. */
@Composable
fun CircleInCorner(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(width = 240.dp, height = 120.dp)
            .background(Color.Black),
    ) {
        Image(
            painter = painterResource(R.drawable.circle),
            contentDescription = stringResource(R.string.circle_description),
            modifier = Modifier.align(Alignment.TopEnd),
        )
    }
}

/** Задание 6.2. Синий блок, круг по центру растянут по размерам блока и перекрашен в фиолетовый. */
@Composable
fun StretchedPurpleCircle(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(width = 240.dp, height = 120.dp)
            .background(Color.Blue),
    ) {
        Image(
            painter = painterResource(R.drawable.circle),
            contentDescription = stringResource(R.string.circle_description),
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.Center,
            contentScale = ContentScale.FillBounds,
            colorFilter = ColorFilter.tint(Color(0xFF800080)),
        )
    }
}

@Preview(name = "CircleInCorner")
@Composable
fun CircleInCornerPreview() {
    CircleInCorner()
}

@Preview(name = "StretchedPurpleCircle")
@Composable
fun StretchedPurpleCirclePreview() {
    StretchedPurpleCircle()
}

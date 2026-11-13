package com.example.module2.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.module2.R

/** Вариант 1: зелёный курсив 16 sp, выравнивание по центру. */
@Composable
fun GreenItalicText(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.compose_description),
        color = Color.Green,
        fontSize = 16.sp,
        fontStyle = FontStyle.Italic,
        textAlign = TextAlign.Center,
        modifier = modifier.fillMaxWidth(),
    )
}

/** Вариант 2: одна строка, не уместившийся текст заменяется многоточием. */
@Composable
fun SingleLineText(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.compose_description),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier,
    )
}

/** Вариант 3: отступ первой строки 48 sp, 24 sp, чёрный текст на зелёном фоне, подчёркивание. */
@Composable
fun IndentedUnderlinedText(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.compose_description),
        style = TextStyle(textIndent = TextIndent(firstLine = 48.sp)),
        fontSize = 24.sp,
        color = Color.Black,
        textDecoration = TextDecoration.Underline,
        modifier = modifier.background(Color.Green),
    )
}

/** Все три варианта на одном экране (для запуска в приложении). */
@Composable
fun TextStylesScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        GreenItalicText()
        SingleLineText()
        IndentedUnderlinedText()
    }
}

@Preview(name = "Зелёный курсив", showBackground = true)
@Composable
fun GreenItalicTextPreview() {
    GreenItalicText()
}

@Preview(name = "Одна строка", showBackground = true)
@Composable
fun SingleLineTextPreview() {
    SingleLineText()
}

@Preview(name = "Отступ и подчёркивание", showBackground = true)
@Composable
fun IndentedUnderlinedTextPreview() {
    IndentedUnderlinedText()
}

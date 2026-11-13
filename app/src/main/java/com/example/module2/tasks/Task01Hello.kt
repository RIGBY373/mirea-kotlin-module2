package com.example.module2.tasks

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.module2.R
import com.example.module2.ui.theme.Module2Theme

/**
 * Задание 1. Приветственная надпись.
 * Если имя не передано (null), выводится «Имя не задано».
 */
@Composable
fun Hello(name: String?, modifier: Modifier = Modifier) {
    val text = if (name != null) {
        stringResource(R.string.hello_template, name)
    } else {
        stringResource(R.string.name_not_set)
    }
    Text(
        text = text,
        fontSize = 24.sp,
        modifier = modifier.padding(16.dp),
    )
}

/**
 * Задание 2. Три макета предпросмотра с системным UI:
 * портретная и альбомная ориентация, а также круглый экран 200×200 dp с жёлтым фоном.
 */
@Preview(
    name = "portrait",
    showSystemUi = true,
    device = "spec:width=411dp,height=891dp",
)
@Preview(
    name = "landscape",
    showSystemUi = true,
    device = "spec:width=411dp,height=891dp,orientation=landscape",
)
@Preview(
    name = "round",
    showSystemUi = true,
    showBackground = true,
    backgroundColor = 0xFFFFFF00,
    device = "spec:width=200dp,height=200dp,dpi=320,isRound=true",
)
@Composable
fun HelloPreview() {
    Module2Theme {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Hello(name = "Android")
        }
    }
}

package com.example.module2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.module2.ui.theme.Module2Theme

class MainActivity : ComponentActivity() {

    private val TAG = "MainActivity" // Тэг для логирования MainActivity

    // Задание 1: имя для приветствия. Для второй сборки здесь указывается null.
    private val userName: String? = "Иван"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate Called")
        enableEdgeToEdge()
        // Номер задания, которое нужно открыть сразу (передаётся через Intent, по умолчанию — меню)
        val startTask = intent.getIntExtra(EXTRA_TASK, 0)
        setContent {
            Module2Theme {
                Module2App(userName = userName, startTask = startTask)
            }
        }
    }

    companion object {
        const val EXTRA_TASK = "task"
    }
}

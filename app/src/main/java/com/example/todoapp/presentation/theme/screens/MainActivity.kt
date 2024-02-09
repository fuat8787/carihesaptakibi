package com.example.todoapp.presentation.theme.screens

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.example.todoapp.presentation.theme.BackgroundColor
import com.example.todoapp.presentation.theme.TodoAppTheme

class MainActivity : ComponentActivity() {
            override fun onCreate(savedInstanceState: Bundle?) {
                        super.onCreate(savedInstanceState)
                        setContent {
                                    TodoAppTheme {
                                                window?.statusBarColor = BackgroundColor.toArgb()
                                                window?.navigationBarColor = BackgroundColor.toArgb()
                                                Surface(
                                                            color = BackgroundColor
,
                                                                    modifier = Modifier
                                                                        .fillMaxSize()
                                                ) {
                                                            MainScreen()
                                                }
                                    }
                        }
            }
}

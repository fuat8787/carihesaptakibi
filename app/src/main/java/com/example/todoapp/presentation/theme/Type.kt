package com.example.todoapp.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.todoapp.R

private val Poppins = FontFamily(
            Font(R.font.poppins_black, weight = FontWeight.Black),
            Font(R.font.poppins_bold, weight = FontWeight.Bold),
            Font(R.font.poppins_regular)
)
val Typography = Typography(
defaultFontFamily = Poppins
)
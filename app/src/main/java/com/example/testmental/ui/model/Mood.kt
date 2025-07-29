package com.example.testmental.ui.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class Mood(
    val label: String,
    val imageVector: ImageVector? = null, // Для Icons.Default
    val drawableRes: Int? = null,         // Для кастомных иконок
    val color: Color
)
package com.example.testmental.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val AppColorTheme = lightColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)


@Composable
fun TestMentalTheme(

    content: @Composable () -> Unit
) {


    MaterialTheme(
        colorScheme = AppColorTheme,
        typography = Typography,
        content = content
    )
}
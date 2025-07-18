package com.example.testmental.screen.navig

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
@Composable
fun NotesScreen() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Экран notes", style = MaterialTheme.typography.headlineMedium)
    }
}
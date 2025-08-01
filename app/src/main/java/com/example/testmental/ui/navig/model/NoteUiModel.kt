package com.example.testmental.ui.navig.model

import java.util.UUID

data class NoteUiModel(
    val id: String,
    val title: String = "",
    val content: String = "",
    val createdAt: Long = System.currentTimeMillis()
)

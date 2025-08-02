package com.example.testmental.ui.navig.model

data class NoteUiModel(
    val id: String,
    val title: String = "",
    val content: String = "",
    val createdAt: Long = System.currentTimeMillis()
)

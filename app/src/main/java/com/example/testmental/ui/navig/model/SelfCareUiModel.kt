package com.example.testmental.ui.navig.model

data class SelfCareUiModel(
    val date: String,
    val mood: String,
    val activities: List<String>,
    val emotions: List<String>,
    val color: String
)
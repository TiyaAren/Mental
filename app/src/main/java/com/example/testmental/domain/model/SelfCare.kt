package com.example.testmental.domain.model

import java.util.UUID

data class SelfCare(
    val id: String = UUID.randomUUID().toString(),
    val date: String,
    val mood: String,
    val emotions: List<String>,
    val activities: List<String>
)

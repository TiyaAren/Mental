package com.example.testmental.domain.model

import android.util.Log

data class MoodEntry(
    val date: String,
    val mood: String,
    val emotions: List<String>,
    val activities: List<String>
){
    init {
        Log.d("MoodEntry", "Создан: mood=${mood}, emotions=$emotions, activities=$activities")
    }
}

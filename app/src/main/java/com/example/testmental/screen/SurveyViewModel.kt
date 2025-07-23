package com.example.testmental.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.testmental.screen.emotions.Mood

class SurveyViewModel : ViewModel() {
    // Выбранное настроение (только одно)
    private val _selectedMood = mutableStateOf<Mood?>(null)
    val selectedMood: Mood? get() = _selectedMood.value

    // Выбранные эмоции (несколько)
    private val _selectedEmotions = mutableStateListOf<String>()
    val selectedEmotions: List<String> get() = _selectedEmotions

    // Выбранные активности (несколько)
    private val _selectedActivities = mutableStateListOf<String>()
    val selectedActivities: List<String> get() = _selectedActivities

    // Устанавливаем настроение
    fun selectMood(mood: Mood) {
        _selectedMood.value = mood
    }

    // Переключение эмоций (мультивыбор)
    fun toggleEmotion(emotion: String) {
        if (_selectedEmotions.contains(emotion)) _selectedEmotions.remove(emotion)
        else _selectedEmotions.add(emotion)
    }

    // Переключение активностей (мультивыбор)
    fun toggleActivity(activity: String) {
        if (_selectedActivities.contains(activity)) _selectedActivities.remove(activity)
        else _selectedActivities.add(activity)
    }

    // Очистка всех данных (если понадобится)
    fun resetAll() {
        _selectedMood.value = null
        _selectedEmotions.clear()
        _selectedActivities.clear()
    }
}

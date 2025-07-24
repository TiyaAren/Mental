package com.example.testmental.screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.testmental.screen.emotions.Mood

class SurveyViewModel : ViewModel() {
    private val _entries = mutableStateListOf<MoodEntry>()
    val entries: List<MoodEntry> get() = _entries

    private val _selectedMood = mutableStateOf<Mood?>(null)
    val selectedMood: Mood? get() = _selectedMood.value

    private val _selectedEmotions = mutableStateListOf<String>()
    val selectedEmotions: List<String> get() = _selectedEmotions

    private val _selectedActivities = mutableStateListOf<String>()
    val selectedActivities: List<String> get() = _selectedActivities

    fun selectMood(mood: Mood) {
        _selectedMood.value = mood
    }

    fun toggleEmotion(emotion: String) {
        if (_selectedEmotions.contains(emotion)) _selectedEmotions.remove(emotion)
        else _selectedEmotions.add(emotion)
    }

    fun toggleActivity(activity: String) {
        if (_selectedActivities.contains(activity)) _selectedActivities.remove(activity)
        else _selectedActivities.add(activity)
    }

    fun resetAll() {
        _selectedMood.value = null
        _selectedEmotions.clear()
        _selectedActivities.clear()
    }
    fun saveCurrentEntry() {
        val entry = MoodEntry(
            mood = _selectedMood.value,
            emotions = _selectedEmotions.toList(),
            activities = _selectedActivities.toList()
        )
        _entries.add(entry)
        resetAll()
    }

}

data class MoodEntry(
    val mood: Mood?,
    val emotions: List<String>,
    val activities: List<String>
){
    init {
        Log.d("MoodEntry", "Создан: mood=${mood?.label}, emotions=$emotions, activities=$activities")
    }
}

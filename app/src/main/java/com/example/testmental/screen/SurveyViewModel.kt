package com.example.testmental.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.testmental.screen.emotions.Mood

class SurveyViewModel : ViewModel() {
    var selectedMood by mutableStateOf<Mood?>(null)
        private set

    var selectedEmotions = mutableStateListOf<String>()
        private set

    var selectedActivities = mutableStateListOf<String>()
        private set

    fun selectMood(mood: Mood) { selectedMood = mood }

    fun toggleEmotion(emotion: String) {
        if (selectedEmotions.contains(emotion)) selectedEmotions.remove(emotion)
        else selectedEmotions.add(emotion)
    }

    fun toggleActivity(activity: String) {
        if (selectedActivities.contains(activity)) selectedActivities.remove(activity)
        else selectedActivities.add(activity)
    }
}

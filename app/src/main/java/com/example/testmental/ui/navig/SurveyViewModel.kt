package com.example.testmental.ui.navig


import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmental.domain.model.SelfCare
import com.example.testmental.domain.repository.SelfCareRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class SurveyViewModel @Inject constructor(
    private val selfCareRepository: SelfCareRepository
) : ViewModel() {

    private val _mood = mutableStateOf<String?>(null)
    val mood: String? get() = _mood.value

    private val _emotions = mutableStateListOf<String>()
    val emotions: List<String> get() = _emotions

    private val _activities = mutableStateListOf<String>()
    val activities: List<String> get() = _activities

    fun setMood(moodLabel: String) {
        _mood.value = moodLabel
        Log.d("SurveyViewModel", "Mood set: $moodLabel")
    }

    fun setEmotions(selected: List<String>) {
        _emotions.clear()
        _emotions.addAll(selected)
        Log.d("SurveyViewModel", "Emotions set: $selected")
    }

    fun setActivities(selected: List<String>) {
        _activities.clear()
        _activities.addAll(selected)
        Log.d("SurveyViewModel", "Activities set: $selected")
    }

    fun saveSelfCareToDb() {
        val currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val currentMood = _mood.value

        if (currentMood.isNullOrEmpty()) {
            Log.d("SurveyViewModel", "Mood is null, skipping save")
            return
        }

        val selfCare = SelfCare(
            id = UUID.randomUUID().toString(),
            date = currentDate,
            mood = currentMood,
            emotions = emotions,
            activities = activities
        )

        Log.d("SurveyViewModel", "Saving to DB: $selfCare")

        viewModelScope.launch {
            selfCareRepository.saveSelfCare(selfCare)
        }
    }
}

package com.example.testmental.ui.navig

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.testmental.domain.model.SelfCare
import com.example.testmental.ui.navig.model.MoodUiModel

class SurveyViewModel : ViewModel() {
    private val _entries = mutableStateListOf<SelfCare>()
    val entries: List<SelfCare> get() = _entries

    private val _selectedMoodUiModel = mutableStateOf<MoodUiModel?>(null)
    val selectedMoodUiModel: MoodUiModel? get() = _selectedMoodUiModel.value

    private val _selectedEmotions = mutableStateListOf<String>()
    val selectedEmotions: List<String> get() = _selectedEmotions

    private val _selectedActivities = mutableStateListOf<String>()
    val selectedActivities: List<String> get() = _selectedActivities

    fun selectMood(moodUiModel: MoodUiModel) {
        _selectedMoodUiModel.value = moodUiModel
    }

    fun toggleEmotion(emotion: String) {
        if (_selectedEmotions.contains(emotion)) _selectedEmotions.remove(emotion)
        else _selectedEmotions.add(emotion)
    }

    fun toggleActivity(activity: String) {
        if (_selectedActivities.contains(activity)) _selectedActivities.remove(activity)
        else _selectedActivities.add(activity)
    }
}
//    fun resetAll() {
//        _selectedMood.value = null
//        _selectedEmotions.clear()
//        _selectedActivities.clear()
//    }
//    fun saveCurrentEntry() {
//        val entry = SelfCare(
//            mood = _selectedMood.value,
//            emotions = _selectedEmotions.toList(),
//            activities = _selectedActivities.toList()
//        )
//        _entries.add(entry)
//        resetAll()
//    }
//
//}


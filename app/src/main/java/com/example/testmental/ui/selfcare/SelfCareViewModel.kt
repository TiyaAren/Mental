package com.example.testmental.ui.selfcare


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmental.domain.model.SelfCare
import com.example.testmental.domain.repository.SelfCareRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelfCareViewModel @Inject constructor(
    private val repository: SelfCareRepository
) : ViewModel() {

    private val _allSelfCare = repository.getAllSelfCare()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allSelfCare: StateFlow<List<SelfCare>> = _allSelfCare

    fun deleteSelfCareById(id: String) {
        viewModelScope.launch {
            repository.deleteSelfCare(id)
            Log.d("SelfCareViewModel", "Deleted entry with id: $id")
        }
    }

    fun getEntryByDate(date: String, onResult: (SelfCare?) -> Unit) {
        viewModelScope.launch {
            val entry = repository.getSelfCareByDate(date)
            onResult(entry)
            Log.d("SelfCareViewModel", "Fetched by date ($date): $entry")
        }
    }
}

package com.example.testmental.ui.dashboard.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmental.domain.model.SelfCare
import com.example.testmental.domain.repository.SelfCareRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: SelfCareRepository
) : ViewModel() {
    val selfCareList: StateFlow<List<SelfCare>> = repository.getAllSelfCare()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    init {
        viewModelScope.launch {
            repository.getAllSelfCare().collect {
                Log.d("HomeViewModel", "Loaded entries: $it")
            }
        }
    }

}

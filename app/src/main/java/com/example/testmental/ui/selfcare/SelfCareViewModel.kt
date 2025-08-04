package com.example.testmental.ui.selfcare


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmental.domain.model.SelfCare
import com.example.testmental.domain.repository.SelfCareRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelfCareViewModel @Inject constructor(
    private val repository: SelfCareRepository
) : ViewModel() {

    fun saveSelfCare(selfCare: SelfCare) {
        viewModelScope.launch {
            repository.saveSelfCare(selfCare)
        }
    }

    fun loadSelfCare(date: String, onResult: (SelfCare?) -> Unit) {
        viewModelScope.launch {
            val result = repository.getSelfCareByDate(date)
            onResult(result)
        }
    }
}

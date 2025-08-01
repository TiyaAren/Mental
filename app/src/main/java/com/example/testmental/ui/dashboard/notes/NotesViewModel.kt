package com.example.testmental.ui.dashboard.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmental.data.repository.NotesRepository
import com.example.testmental.ui.navig.model.NoteUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val repository: NotesRepository
) : ViewModel() {
    val notes = repository.notes

    fun getNoteById(id: String) = repository.getNoteById(id)

    fun createNote(onCreated: (Int) -> Unit) {
        viewModelScope.launch {
            val note = NoteEntity(
                title = "",
                content = "",NoteEntity
                createdAt = System.currentTimeMillis()
            )
            val newId = repository.insertNote(note)
            onCreated(newId.toInt())
        }
    }


    fun updateNote(note: NoteUiModel) = repository.updateNote(note)
}

package com.example.testmental.ui.dashboard.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmental.data.mapper.toDomainModel
import com.example.testmental.data.mapper.toUiModel
import com.example.testmental.domain.model.Note
import com.example.testmental.domain.repository.NotesRepository
import com.example.testmental.ui.navig.model.NoteUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val repository: NotesRepository
) : ViewModel() {

    val notes = repository.getAllNotes()
        .map { list -> list.map { it.toUiModel() } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun getNoteById(id: String): NoteUiModel? {
        return notes.value.find { it.id == id }
    }

    fun addNote(note: Note) {

        viewModelScope.launch {
            repository.addNote(note)
        }
    }

    fun updateNote(note: NoteUiModel) {
        viewModelScope.launch {
            repository.updateNote(note.toDomainModel())
        }
    }
    fun deleteNotes(noteIds: List<String>) {
        viewModelScope.launch {
            repository.deleteNotesByIds(noteIds)
            // loadNotes() не нужен, т.к. notes — это Flow и сам обновится
        }
    }


}
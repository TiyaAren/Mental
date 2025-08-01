package com.example.testmental.ui.dashboard.notes

import androidx.lifecycle.ViewModel
import com.example.testmental.data.repository.NotesRepository
import com.example.testmental.ui.navig.model.NoteUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val repository: NotesRepository
) : ViewModel() {

    val notes = repository.notes

    fun getNoteById(id: String) = repository.getNoteById(id)

    fun createNote(title: String, content: String): NoteUiModel {
        return repository.addNote(title, content)
    }

    fun updateNote(note: NoteUiModel) = repository.updateNote(note)
}

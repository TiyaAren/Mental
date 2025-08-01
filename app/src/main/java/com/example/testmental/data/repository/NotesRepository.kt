package com.example.testmental.data.repository

import com.example.testmental.ui.navig.model.NoteUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID
import javax.inject.Inject


class NotesRepository @Inject constructor() {
    private val _notes = MutableStateFlow<List<NoteUiModel>>(emptyList())
    val notes: StateFlow<List<NoteUiModel>> = _notes

    fun getNoteById(id: String): NoteUiModel? = _notes.value.find { it.id == id }

    fun addNote(title: String, content: String): NoteUiModel {
        val newNote = NoteUiModel(
            id = UUID.randomUUID().toString(),
            title = title,
            content = content,
            createdAt = System.currentTimeMillis()
        )
        _notes.value = _notes.value + newNote
        return newNote
    }

    fun updateNote(note: NoteUiModel) {
        _notes.value = _notes.value.map {
            if (it.id == note.id) note else it
        }
    }
}

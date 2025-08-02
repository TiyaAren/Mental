package com.example.testmental.data.repository.impl

import com.example.testmental.domain.model.Note
import com.example.testmental.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor() : NotesRepository {

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    private val notesFlow = _notes.asStateFlow()

    override fun getNotes(): Flow<List<Note>> = notesFlow

    override suspend fun getNoteById(id: String): Note? =
        _notes.value.find { it.id == id }

    override suspend fun addNote(note: Note) {
        _notes.value += note
    }

    override suspend fun updateNote(note: Note) {
        _notes.value = _notes.value.map {
            if (it.id == note.id) note else it
        }
    }
}

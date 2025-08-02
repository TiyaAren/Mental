package com.example.testmental.domain.repository


import com.example.testmental.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    fun getNotes(): Flow<List<Note>>
    suspend fun getNoteById(id: String): Note?
    suspend fun addNote(note: Note)
    suspend fun updateNote(note: Note)
}

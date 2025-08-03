package com.example.testmental.data.repository.impl

import com.example.testmental.data.local.NoteDao
import com.example.testmental.data.mapper.toDomain
import com.example.testmental.data.mapper.toEntity
import com.example.testmental.domain.model.Note
import com.example.testmental.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NotesRepository {

    override fun getAllNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes().map { list -> list.map { it.toDomain() } }
    }

    override suspend fun getNoteById(id: String): Note? {
        return noteDao.getNoteById(id)?.toDomain()
    }

    override suspend fun addNote(note: Note) {
        noteDao.insert(note.toEntity())
    }

    override suspend fun updateNote(note: Note) {
        noteDao.update(note.toEntity())
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.delete(note.toEntity())
    }
}


package com.example.testmental.domain.usecase


import com.example.testmental.domain.model.Note
import com.example.testmental.domain.repository.NotesRepository
import java.util.UUID

class CreateNoteUseCase(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(title: String, content: String): Note {
        val newNote = Note(
            id = UUID.randomUUID().toString(),
            title = title,
            content = content,
            date = System.currentTimeMillis().toString()
        )
        repository.addNote(newNote)
        return newNote
    }
}

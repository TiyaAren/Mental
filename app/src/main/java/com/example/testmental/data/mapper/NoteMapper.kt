package com.example.testmental.data.mapper

import com.example.testmental.data.model.NoteEntity
import com.example.testmental.domain.model.Note
import com.example.testmental.ui.navig.model.NoteUiModel

fun NoteEntity.toDomain(): Note {
    return Note(id, title, content, date)
}

fun Note.toEntity(): NoteEntity {
    return NoteEntity(id, title, content, date)
}


fun Note.toUiModel(): NoteUiModel {
    return NoteUiModel(
        id = id,
        title = title,
        content = content,
        createdAt = date.toLongOrNull() ?: System.currentTimeMillis()
    )
}

fun NoteUiModel.toDomainModel(): Note {
    return Note(
        id = id,
        title = title,
        content = content,
        date = createdAt.toString()
    )
}

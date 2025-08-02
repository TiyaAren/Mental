package com.example.testmental.data.mapper

import com.example.testmental.domain.model.Note
import com.example.testmental.ui.navig.model.NoteUiModel

fun Note.toUiModel(): NoteUiModel {
    return NoteUiModel(
        id = id,
        title = title,
        content = content,
        createdAt = createdAt
    )
}

fun NoteUiModel.toDomainModel(): Note {
    return Note(
        id = id,
        title = title,
        content = content,
        createdAt = createdAt
    )
}
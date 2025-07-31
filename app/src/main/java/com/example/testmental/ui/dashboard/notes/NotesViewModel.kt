package com.example.testmental.ui.dashboard.notes

import androidx.lifecycle.ViewModel
import com.example.testmental.data.repository.NotesRepository
import com.example.testmental.ui.navig.model.NoteUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val repository: NotesRepository
) : ViewModel() {

    val notes: StateFlow<List<NoteUiModel>> = repository.notes

    fun getNoteById(id: String): NoteUiModel? = repository.getNoteById(id)

    fun createNote(): NoteUiModel = repository.addNote()

    fun updateNote(note: NoteUiModel) = repository.updateNote(note)
}

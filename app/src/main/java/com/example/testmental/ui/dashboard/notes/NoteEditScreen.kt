package com.example.testmental.ui.dashboard.notes

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController


@Composable
fun NoteEditScreen(
    noteId: String,
    navController: NavController,
    viewModel: NotesViewModel = hiltViewModel()
) {
    val notes by viewModel.notes.collectAsState()
    val note = notes.find { it.id == noteId }

    if (note == null) {
        Text("Заметка не найдена")
        return
    }

    var title by remember(note.id) { mutableStateOf(note.title) }
    var content by remember(note.id) { mutableStateOf(note.content) }

    NoteForm(
        title = title,
        onTitleChange = { title = it },
        content = content,
        onContentChange = { content = it },
        onSaveClick = {
            if (title != note.title || content != note.content) {
                viewModel.updateNote(note.copy(title = title, content = content))
            }
            navController.popBackStack()
        },
        onBackClick = { navController.popBackStack() },
        buttonText = "Сохранить"
    )
}

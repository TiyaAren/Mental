package com.example.testmental.ui.dashboard.notes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.testmental.domain.model.Note
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

@Composable
fun NoteCreateScreen(
    navController: NavController,
    viewModel: NotesViewModel = hiltViewModel()
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    NoteForm(
        title = title,
        onTitleChange = { title = it },
        content = content,
        onContentChange = { content = it },
        onSaveClick = {
            if (title.isNotBlank() || content.isNotBlank()) {
                val newNote = Note(
                    id = UUID.randomUUID().toString(),
                    title = title,
                    content = content,
                    date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
                )
                viewModel.addNote(newNote)
            }
            navController.popBackStack()
        },
        onBackClick = { navController.popBackStack() },
        buttonText = "Создать"
    )
}

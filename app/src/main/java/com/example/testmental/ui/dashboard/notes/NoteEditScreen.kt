package com.example.testmental.ui.dashboard.notes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.testmental.ui.navig.model.NoteUiModel

@Composable
fun NoteEditScreen(
    noteId: String,
    navController: NavController,
    viewModel: NotesViewModel = hiltViewModel()
) {
    val note by produceState<NoteUiModel?>(initialValue = null, noteId) {
        value = viewModel.getNoteById(noteId)
    }

    if (note == null) {
        Text("Заметка не найдена")
        return
    }

    var title by remember { mutableStateOf(note!!.title) }
    var content by remember { mutableStateOf(note!!.content) }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Название") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Содержание") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            viewModel.updateNote(note!!.copy(title = title, content = content))
            navController.navigate("edit_note") {
                popUpTo("edit_note/${noteId}") { inclusive = true }
            }
        }) {
            Text("Сохранить")
        }
    }
}

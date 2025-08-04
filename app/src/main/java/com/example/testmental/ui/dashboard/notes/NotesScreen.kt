@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.testmental.ui.dashboard.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.SelectAll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import java.util.Date

@Composable
fun NotesScreen(
    navController: NavController,
    viewModel: NotesViewModel = hiltViewModel()
) {
    val notes by viewModel.notes.collectAsState()
    var selectionMode by remember { mutableStateOf(false) }
    var selectedNotes by remember { mutableStateOf(setOf<String>()) }
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(
                            text = "Заметки",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                        )
                    },
                    actions = {
                        if (selectionMode) {
                            IconButton(onClick = {
                                selectedNotes = emptySet()
                                selectionMode = false
                            }) {
                                Icon(Icons.Default.Close, contentDescription = "Отменить")
                            }
                            IconButton(onClick = {
                                showDialog = true
                            }) {
                                Icon(Icons.Default.Delete, contentDescription = "Удалить")
                            }
                        } else {
                            IconButton(onClick = {
                                selectedNotes = notes.map { it.id }.toSet()
                                selectionMode = true
                            }) {
                                Icon(Icons.Default.SelectAll, contentDescription = "Выбрать все")
                            }
                        }
                    }
                )
                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color.Gray.copy(alpha = 0.3f)
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("note_create")
            }) {
                Icon(Icons.Default.Add, contentDescription = "Добавить")
            }
        }
    ) { padding ->

        // Диалог подтверждения удаления
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Удалить заметки") },
                text = { Text("Вы точно хотите удалить ${selectedNotes.size} заметок?") },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.deleteNotes(selectedNotes.toList())
                        selectedNotes = emptySet()
                        selectionMode = false
                        showDialog = false
                    }) {
                        Text("Да")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        selectedNotes = emptySet()
                        selectionMode = false
                        showDialog = false
                    }) {
                        Text("Нет")
                    }
                }

            )
        }

        LazyColumn(contentPadding = padding) {
            items(notes) { note ->
                val isSelected = note.id in selectedNotes
                val backgroundColor = if (isSelected) Color(0xFFB3E5FC) else Color.Transparent

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(backgroundColor)
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onLongPress = {
                                    if (!selectionMode) {
                                        selectionMode = true
                                        selectedNotes = setOf(note.id)
                                    }
                                },
                                onTap = {
                                    if (selectionMode) {
                                        selectedNotes = if (isSelected)
                                            selectedNotes - note.id
                                        else
                                            selectedNotes + note.id
                                    } else {
                                        navController.navigate("edit_note/${note.id}")
                                    }
                                }
                            )
                        }
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    if (selectionMode) {
                        Checkbox(
                            checked = isSelected,
                            onCheckedChange = {
                                selectedNotes = if (isSelected)
                                    selectedNotes - note.id
                                else
                                    selectedNotes + note.id
                            },
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }

                    Column {
                        Text(note.title.ifEmpty { "Без названия" }, style = MaterialTheme.typography.titleMedium)
                        Text("Дата: ${Date(note.createdAt)}", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}

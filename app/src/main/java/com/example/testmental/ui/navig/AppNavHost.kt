package com.example.testmental.ui.navig

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testmental.ui.dashboard.notes.NoteEditScreen
import com.example.testmental.ui.dashboard.notes.NotesScreen
import com.example.testmental.ui.dashboard.notes.NotesViewModel
import com.example.testmental.ui.selfcare.activity.ActivitiesScreen
import com.example.testmental.ui.selfcare.emotion.EmotionScreen
import com.example.testmental.ui.selfcare.mood.MoodScreen


@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "start"
    ) {
        composable("start") {
            val surveyViewModel: SurveyViewModel = hiltViewModel()
            StartSurveyScreen(navController)
        }

        composable("mood/{date}") { backStackEntry ->
            val surveyViewModel: SurveyViewModel = hiltViewModel()
            val date = backStackEntry.arguments?.getString("date")
            MoodScreen(navController, surveyViewModel, date)
        }

        composable("emotion") {
            val surveyViewModel: SurveyViewModel = hiltViewModel()
            EmotionScreen(navController, surveyViewModel)
        }

        composable("activity") {
            val surveyViewModel: SurveyViewModel = hiltViewModel()
            ActivitiesScreen(navController, surveyViewModel)
        }

        composable("main") {
            val surveyViewModel: SurveyViewModel = hiltViewModel()
            MainNavigationScreen()
            MainNavigationScreen( )
        }
        composable("noteUiModels") {
            NotesScreen(navController)
        }
        composable("edit_note/{noteId}") { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId") ?: return@composable
            val notesViewModel: NotesViewModel = hiltViewModel()
            NoteEditScreen(
                noteId = noteId,
                viewModel = notesViewModel,
                navController = navController
            )
        }
    }
}

package com.example.testmental.ui.navig

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.testmental.ui.dashboard.notes.NoteCreateScreen
import com.example.testmental.ui.dashboard.notes.NoteEditScreen
import com.example.testmental.ui.selfcare.activity.ActivitiesScreen
import com.example.testmental.ui.selfcare.emotion.EmotionScreen
import com.example.testmental.ui.selfcare.mood.MoodScreen


@Composable
fun AppNavHost(navController: NavHostController) {
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
            MainNavigationScreen(navController) // передаём главный
        }

        composable("note_create") {
            NoteCreateScreen(navController)
        }
        composable("notes") {
            MainNavigationScreen(navController)
        }


        composable(
            route = "edit_note/{noteId}",
            arguments = listOf(navArgument("noteId") { type = NavType.StringType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId")
            Log.d("TAG", "edit_note screen opened, noteId = $noteId")
            if (noteId == null) {
                navController.popBackStack()
                return@composable
            }

            NoteEditScreen(noteId = noteId, navController = navController)
        }


    }
}

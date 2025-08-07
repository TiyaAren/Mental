package com.example.testmental.ui.navig

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.testmental.ui.auth.UserViewModel
import com.example.testmental.ui.auth.log.SignInScreen
import com.example.testmental.ui.auth.reg.SignUpScreen
import com.example.testmental.ui.dashboard.notes.NoteCreateScreen
import com.example.testmental.ui.dashboard.notes.NoteEditScreen
import com.example.testmental.ui.selfcare.activity.ActivitiesScreen
import com.example.testmental.ui.selfcare.emotion.EmotionScreen
import com.example.testmental.ui.selfcare.mood.MoodScreen


@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "signUp"
//        startDestination = "start"
    ) {
        composable("startSurvey") {
            StartSurveyScreen(navController)
        }

        composable("signUp") {
            val viewModel: UserViewModel = hiltViewModel()
            SignUpScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable("signIn") {
            val viewModel: UserViewModel = hiltViewModel()
            SignInScreen(navController, viewModel)
        }

        // 🔽 Вложенный граф с общим SurveyViewModel
        navigation(
            route = "selfcare_graph",
            startDestination = "mood/{date}"
        ) {
            composable(
                route = "mood/{date}",
                arguments = listOf(navArgument("date") { type = NavType.StringType })
            ) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("selfcare_graph")
                }
                val date = backStackEntry.arguments?.getString("date")
                val viewModel: SurveyViewModel = hiltViewModel(parentEntry)
                MoodScreen(navController, viewModel, date)
            }

            composable("emotion") { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("selfcare_graph")
                }
                val viewModel: SurveyViewModel = hiltViewModel(parentEntry)
                EmotionScreen(navController, viewModel)
            }

            composable("activity") { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("selfcare_graph")
                }
                val viewModel: SurveyViewModel = hiltViewModel(parentEntry)
                ActivitiesScreen(navController, viewModel)
            }
        }

        composable("main") {
            MainNavigationScreen(navController)
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

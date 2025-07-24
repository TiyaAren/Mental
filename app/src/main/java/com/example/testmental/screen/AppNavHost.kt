package com.example.testmental.screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.testmental.MainScreen
import com.example.testmental.screen.emotions.ActivitiesScreen
import com.example.testmental.screen.emotions.Emotion
import com.example.testmental.screen.emotions.MoodScreen


@Composable
fun AppNavHost(navController: NavHostController, surveyViewModel: SurveyViewModel = viewModel()) {
    NavHost(navController, startDestination = "start") {
        composable("start") {
            StartSurveyScreen(navController)
        }
        composable("mood/{date}") { backStackEntry ->
            val date = backStackEntry.arguments?.getString("date")
            MoodScreen(navController, surveyViewModel, date)
        }
        composable("emotion") { Emotion(navController, surveyViewModel) }
        composable("activity") { ActivitiesScreen(navController, surveyViewModel) }
        composable("main") { MainScreen(navController, surveyViewModel) }
    }
}

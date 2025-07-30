package com.example.testmental.ui.navig

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.testmental.ui.selfcare.activity.ActivitiesScreen
import com.example.testmental.ui.selfcare.emotion.EmotionScreen
import com.example.testmental.ui.selfcare.mood.MoodScreen


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
        composable("emotion") { EmotionScreen(navController, surveyViewModel) }
        composable("activity") { ActivitiesScreen(navController, surveyViewModel) }
        composable("main") { MainNavigationScreen(navController, surveyViewModel) }
    }
}

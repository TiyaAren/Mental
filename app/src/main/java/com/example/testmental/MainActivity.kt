package com.example.testmental

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.testmental.screen.AppNavHost
import com.example.testmental.screen.emotions.ActivitiesScreen
import com.example.testmental.screen.emotions.MoodScreen
import com.example.testmental.ui.theme.TestMentalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestMentalTheme {
                val navController = rememberNavController()
                AppNavHost(navController)
            }
        }
    }
}


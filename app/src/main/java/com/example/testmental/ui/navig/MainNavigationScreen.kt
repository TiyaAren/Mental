package com.example.testmental.ui.navig

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Note
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.testmental.ui.dashboard.calender.CalendarScreen
import com.example.testmental.ui.dashboard.home.HomeScreen
import com.example.testmental.ui.dashboard.notes.NotesScreen
import com.example.testmental.ui.dashboard.profile.ProfileScreen

sealed class BottomNavScreen(val route: String, val title: String, val icon: ImageVector) {
    object Home : BottomNavScreen("home", "Главная", Icons.Default.Home)
    object Calendar : BottomNavScreen("calendar", "Календарь", Icons.Default.CalendarToday)
    object Notes : BottomNavScreen("notes", "Заметки", Icons.AutoMirrored.Filled.Note)
    object Profile : BottomNavScreen("profile", "Профиль", Icons.Default.Person)


}

@Composable
fun MainNavigationScreen(navController: NavController, moodViewModel: SurveyViewModel) {
    val navController = rememberNavController()
    val items = listOf(
        BottomNavScreen.Home,
        BottomNavScreen.Calendar,
        BottomNavScreen.Notes,
        BottomNavScreen.Profile

    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.title) },
                        label = { Text(screen.title) },
                        selected = currentDestination?.route == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Чтобы не создавать кучу одинаковых экранов в стеке
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        alwaysShowLabel = false
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = BottomNavScreen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(BottomNavScreen.Home.route) { HomeScreen() }
            composable(BottomNavScreen.Calendar.route) { CalendarScreen(navController) }
            composable(BottomNavScreen.Notes.route) { NotesScreen() }
            composable(BottomNavScreen.Profile.route) { ProfileScreen() }

        }
    }
}




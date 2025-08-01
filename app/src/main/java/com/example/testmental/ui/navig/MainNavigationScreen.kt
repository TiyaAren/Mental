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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.testmental.ui.dashboard.calender.CalendarScreen
import com.example.testmental.ui.dashboard.home.HomeScreen
import com.example.testmental.ui.dashboard.notes.NoteEditScreen
import com.example.testmental.ui.dashboard.notes.NotesScreen
import com.example.testmental.ui.dashboard.notes.NotesViewModel
import com.example.testmental.ui.dashboard.profile.ProfileScreen


sealed class BottomNavScreen(val route: String, val title: String, val icon: ImageVector) {
    object Home : BottomNavScreen("home", "Главная", Icons.Default.Home)
    object Calendar : BottomNavScreen("calendar", "Календарь", Icons.Default.CalendarToday)
    object Notes : BottomNavScreen("notes", "Заметки", Icons.AutoMirrored.Filled.Note)
    object Profile : BottomNavScreen("profile", "Профиль", Icons.Default.Person)
}

// Компонент BottomBar, принимает navController
@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(
        BottomNavScreen.Home,
        BottomNavScreen.Calendar,
        BottomNavScreen.Notes,
        BottomNavScreen.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        // Чтобы не создавать дубликаты и сохранить состояние
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                label = { Text(screen.title) },
                icon = { Icon(screen.icon, contentDescription = screen.title) }
            )
        }
    }
}

// Главный экран с навигацией и нижним баром
@Composable
fun MainNavigationScreen(mainNavController: NavHostController) {
    val bottomNavController = rememberNavController() // свой NavController для нижней навигации

    Scaffold(
        bottomBar = { BottomBar(bottomNavController) }
    ) { paddingValues ->
        NavHost(
            navController = bottomNavController,
            startDestination = BottomNavScreen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(BottomNavScreen.Home.route) {
                HomeScreen()
            }
            composable(BottomNavScreen.Calendar.route) {
                CalendarScreen(bottomNavController)
            }
            composable(BottomNavScreen.Notes.route) {
                // Ключевой момент: в экран заметок передаём **главный navController**
                NotesScreen(navController = mainNavController)
            }
            composable(BottomNavScreen.Profile.route) {
                ProfileScreen()
            }
        }
    }
}



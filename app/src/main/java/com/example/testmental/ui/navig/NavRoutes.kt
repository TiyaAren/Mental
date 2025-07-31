package com.example.testmental.ui.navig

sealed class NavRoutes(val route: String) {
    object Start : NavRoutes("start")
    object Mood : NavRoutes("mood/{date}") {
        fun passDate(date: String) = "mood/$date"
    }
    object Emotion : NavRoutes("emotion")
    object Activity : NavRoutes("activity")
    object Main : NavRoutes("main")

    object Notes : NavRoutes("noteUiModels")
    object EditNote : NavRoutes("edit_note/{noteId}") {
        fun passNoteId(id: String) = "edit_note/$id"
    }
}

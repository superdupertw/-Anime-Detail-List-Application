package com.example.composesubmission.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object About: Screen("about")
    object DetailMonoChar : Screen("home/{charId}") {
        fun createRoute(charId: String) = "home/$charId"
    }
}
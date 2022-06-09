package com.example.dogadoptapp.navigation

sealed class Screen(val route: String) {
    object Splash: Screen("splash_screen")
    object Welcome: Screen("welcome_screen")
    object Home: Screen("home_screen")
    // /-is required argument, {}-argument key
    object Details: Screen("details_screen/{dogId}") {
        fun passDogId(dogId: Int): String {
            return "details_screen/$dogId"
        }
    }
    object Search: Screen("search_screen")
    object Overview: Screen("overview_screen")
}

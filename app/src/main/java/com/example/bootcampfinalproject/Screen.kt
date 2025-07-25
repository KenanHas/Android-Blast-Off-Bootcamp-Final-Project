package com.example.bootcampfinalproject

// Örneğin, Screen.kt dosyanız
sealed class Screen(val route: String) {
    object Landing : Screen("landing_screen")
    object Start : Screen("start_screen")
    object Settings : Screen("settings_screen")
    object Score : Screen("score_screen")
    // Game ekranını argüman alacak şekilde güncelliyoruz
    object Game : Screen("game_screen/{difficulty}/{userName}") {
        // Bu fonksiyon, rotayı argümanla birlikte oluşturmak için kullanılır
        fun createRoute(difficulty: String, userName: String): String {
            return "game_screen/$difficulty/$userName"
        }
    }
}
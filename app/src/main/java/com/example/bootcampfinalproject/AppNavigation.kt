package com.example.bootcampfinalproject

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bootcampfinalproject.ui.screens.GameScreen
import com.example.bootcampfinalproject.ui.screens.LandingScreen
import com.example.bootcampfinalproject.ui.screens.ScoreScreen
import com.example.bootcampfinalproject.ui.screens.SettingsScreen
import com.example.bootcampfinalproject.ui.screens.StartScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Landing.route
    ) {
        composable(Screen.Landing.route) {
            LandingScreen(navController = navController)
        }
        composable(Screen.Start.route) {
            StartScreen(navController = navController)
        }
        composable(Screen.Settings.route) {
            SettingsScreen(navController = navController)
        }
        composable(Screen.Score.route) {
            ScoreScreen(navController = navController)
        }

        // !!! BURADA DEĞİŞİKLİK YAPIYORUZ !!!
        composable(
            route = Screen.Game.route, // Screen objenizdeki route'u kullanın
            arguments = listOf(navArgument("difficulty") {
                type = NavType.StringType
                defaultValue = "EASY" // Varsayılan değer
            })
        ) { backStackEntry ->
            val difficulty = backStackEntry.arguments?.getString("difficulty") ?: "EASY"
            // GameScreen'e zorluk seviyesini iletiyoruz
            GameScreen(navController = navController, difficulty = difficulty)
        }
    }
}
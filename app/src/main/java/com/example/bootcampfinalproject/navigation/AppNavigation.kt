package com.example.bootcampfinalproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bootcampfinalproject.ThemeViewModel
import com.example.bootcampfinalproject.ui.screens.game.view.GameScreen
import com.example.bootcampfinalproject.ui.screens.landing.LandingScreen
import com.example.bootcampfinalproject.ui.screens.score.view.ScoreScreen
import com.example.bootcampfinalproject.ui.screens.settings.SettingsScreen
import com.example.bootcampfinalproject.ui.screens.start.StartScreen

@Composable
fun AppNavigation(themeViewModel: ThemeViewModel) {
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
            SettingsScreen(navController = navController, themeViewModel)
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
            }, navArgument("userName") { type = NavType.StringType })
        ) { backStackEntry ->
            val difficulty = backStackEntry.arguments?.getString("difficulty") ?: "EASY"
            val userName = backStackEntry.arguments?.getString("userName") ?: ""
            // GameScreen'e zorluk seviyesini iletiyoruz
            GameScreen(navController = navController, difficulty = difficulty, userName = userName)
        }
    }
}
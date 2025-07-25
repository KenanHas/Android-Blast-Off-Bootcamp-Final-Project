package com.example.bootcampfinalproject.ui.screens.landing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bootcampfinalproject.Screen
import com.example.bootcampfinalproject.ui.components.StartPageButton
import com.example.bootcampfinalproject.ui.components.TitleThinText

@Composable
fun LandingScreen(navController: NavController){
Column (modifier = Modifier
.fillMaxSize()
.background(color = Color.Black)
){
    // Greeting Composable'ına Modifier'ı ilet
    Greeting(
        navController = navController,
        modifier = Modifier.fillMaxSize(),
        titleFirst = "Oyun",
        titleSecond = "Kur"
    )
}}

@Composable
fun Greeting(navController: NavController, modifier: Modifier = Modifier, titleFirst: String, titleSecond: String,) {
    Column (
        modifier = modifier
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        // Başlıklar
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            TitleThinText(
                text = titleFirst,
                color = Color.Green,
                fontSize = 40.sp
            )

            TitleThinText(
                text = titleSecond,
                modifier = Modifier.offset(y = 7.dp),
                color = Color.Green,
                fontSize = 40.sp
            )
        }

        Column (
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            StartPageButton(
                modifier = Modifier.padding(vertical = 10.dp),
                {
                    navController.navigate("start_screen")
                },
                buttonText = "Başla"
            )

            StartPageButton(
                modifier = Modifier.padding(vertical = 10.dp),
                {
                    navController.navigate(Screen.Score.route)
                },
                buttonText = "Skorlarım"
            )

            StartPageButton(
                modifier = Modifier.padding(vertical = 10.dp),
                {
                    navController.navigate(Screen.Settings.route)
                },
                buttonText = "Ayarlar"
            )
        }
    }
}
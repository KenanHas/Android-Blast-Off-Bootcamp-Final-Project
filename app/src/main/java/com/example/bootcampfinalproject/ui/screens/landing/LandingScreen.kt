package com.example.bootcampfinalproject.ui.screens.landing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bootcampfinalproject.Screen
import com.example.bootcampfinalproject.ui.components.StartPageButton
import com.example.bootcampfinalproject.ui.components.TopBarDesign

@Composable
fun LandingScreen(navController: NavController){
    Scaffold { innerPadding->
        Column (modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(color = Color.Black)
        ){
            Greeting(
                navController = navController,
                modifier = Modifier.fillMaxSize(),
                title = "Oyun Kur"
            )
        }
    }
}
@Composable
fun Greeting(navController: NavController, modifier: Modifier = Modifier, title: String) {
    Column (
        modifier = modifier
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ){

        Column {

            TopBarDesign(navController, title, false)

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
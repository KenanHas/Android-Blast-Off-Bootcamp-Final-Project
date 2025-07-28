package com.example.bootcampfinalproject.ui.screens.landing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bootcampfinalproject.R
import com.example.bootcampfinalproject.navigation.Screen
import com.example.bootcampfinalproject.ui.components.StartPageButton
import com.example.bootcampfinalproject.ui.components.TopBarDesign

@Composable
fun LandingScreen(navController: NavController){
    Scaffold { innerPadding->
        Column (modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
        ){
            Greeting(
                navController = navController,
                modifier = Modifier.fillMaxSize(),
                title = stringResource(id = R.string.set_game)
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
                buttonText = stringResource(id = R.string.start)
            )

            StartPageButton(
                modifier = Modifier.padding(vertical = 10.dp),
                {
                    navController.navigate(Screen.Score.route)
                },
                buttonText = stringResource(id = R.string.my_scores)
            )

            StartPageButton(
                modifier = Modifier.padding(vertical = 10.dp),
                {
                    navController.navigate(Screen.Settings.route)
                },
                buttonText = stringResource(id = R.string.settings)
            )
        }
    }
}
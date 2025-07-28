package com.example.bootcampfinalproject.ui.screens.start

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bootcampfinalproject.Screen
import com.example.bootcampfinalproject.ui.components.SegmentedSelectionButton
import com.example.bootcampfinalproject.ui.components.StartPageButton
import com.example.bootcampfinalproject.ui.components.TopBarDesign

@Composable
fun StartScreen(navController: NavController){
    Scaffold { innerPadding->
        Column (modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
        ){
            StartPageUi(navController, modifier = Modifier, "Oyun Kur")
        }
    }
}

@Composable
fun StartPageUi(navController: NavController, modifier: Modifier = Modifier, title: String){
    var selectedDifficulty by remember { mutableStateOf("EASY") }
    var userName by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column (
        modifier = modifier
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ){
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            TopBarDesign(navController, title, true)
        }

        Column (
            Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            TextField(
                value = userName,
                onValueChange = { value ->
                    userName = value

                },
                modifier = Modifier.fillMaxWidth(0.8f),
                shape = CutCornerShape(18.dp),
                singleLine = true,
                label = {
                    Text(
                        text = "Kullanıcı Adı"
                    )
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            SegmentedSelectionButton(
                options = listOf("EASY", "HARD"),
                selectedOption = selectedDifficulty,
                onOptionSelected = { newOption ->
                    selectedDifficulty = newOption
                },
                modifier = Modifier.fillMaxWidth(0.8f)
            )
        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            StartPageButton(modifier = Modifier.fillMaxWidth(0.8f), action = {
                if (userName.isNotEmpty())
                navController.navigate(Screen.Game.createRoute(selectedDifficulty, userName))
                else
                    Toast.makeText(context, "Lütfen Bir Kullanıcı Adı Giriniz.", Toast.LENGTH_LONG).show()
            }, buttonText = "Başla")
        }
    }
}

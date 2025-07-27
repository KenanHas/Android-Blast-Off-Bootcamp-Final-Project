package com.example.bootcampfinalproject.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bootcampfinalproject.data.local.database.LocalAppDatabase
import com.example.bootcampfinalproject.ui.components.StartPageButton
import com.example.bootcampfinalproject.ui.components.TopBarDesign
import com.example.bootcampfinalproject.ui.screens.start.SegmentedSelectionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun SettingsScreen(navController: NavController){

    Scaffold { innerPadding->
        Column (
            modifier = Modifier
            .padding(innerPadding)
            .background(color = Color.Black),
        ){
            SettingsPageUi(navController, modifier = Modifier, title = "Ayarlar Sayfası")
        }
    }
}

@Composable
fun SettingsPageUi(navController: NavController, modifier: Modifier = Modifier, title: String) {
    var selectedMode by remember { mutableStateOf("LIGHT") }
    var open by remember { mutableStateOf("OPEN") }

    val db = LocalAppDatabase.current
    val userDao = db.scoreDao()
    val coroutineScope = rememberCoroutineScope()

    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Column (modifier = modifier.padding(top = 20.dp)){
            TopBarDesign(navController, title, true)
        }

        Column {
            SegmentedSelectionButton(
                options = listOf("LIGHT", "DARK"),
                selectedOption = selectedMode,
                onOptionSelected = { newOption ->
                    selectedMode = newOption
                },
                modifier = Modifier.fillMaxWidth(0.8f)// Ekranın %80'ini kaplasın
            )

            SegmentedSelectionButton(
                options = listOf("OPEN", "CLOSE"),
                selectedOption = open,
                onOptionSelected = { newOption ->
                    open = newOption
                },
                modifier = Modifier.fillMaxWidth(0.8f).offset(y = 15.dp) // Ekranın %80'ini kaplasın
            )
        }

        Column {
            StartPageButton(modifier =  Modifier.padding(bottom = 20.dp), action = {
                coroutineScope.launch {
                    withContext(Dispatchers.IO) {
                        userDao.deleteAllScores()
                    }
                }
            }, buttonText = "Skorları Temizle")
            StartPageButton(modifier =  Modifier.padding(bottom = 20.dp), action = {navController.navigateUp()}, buttonText = "Anasayfaya Dön")
        }

    }
}

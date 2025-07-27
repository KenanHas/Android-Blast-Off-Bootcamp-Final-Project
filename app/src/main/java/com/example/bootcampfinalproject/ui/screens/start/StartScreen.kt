package com.example.bootcampfinalproject.ui.screens.start

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bootcampfinalproject.Screen
import com.example.bootcampfinalproject.ui.components.StartPageButton
import com.example.bootcampfinalproject.ui.components.TopBarDesign

@Composable
fun StartScreen(navController: NavController){
    Scaffold { innerPadding->
        Column (modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(color = Color.Black)
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

@Composable
fun SegmentedSelectionButton(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val containerColor = Color(0xFF4CAF50)
    val selectedSegmentColor = Color.DarkGray
    val unselectedTextColor = Color.White.copy(alpha = 0.7f)
    val selectedTextColor = Color.White

    Box(
        modifier = modifier
            .height(40.dp)
            .offset(y = 10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(containerColor)
            .padding(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            options.forEach { option ->
                val isSelected = option == selectedOption

                val animatedSegmentColor by animateColorAsState(
                    targetValue = if (isSelected) selectedSegmentColor else Color.Transparent,
                    animationSpec = tween(durationMillis = 300), label = "SegmentColorAnimation"
                )

                val animatedTextColor by animateColorAsState(
                    targetValue = if (isSelected) selectedTextColor else unselectedTextColor,
                    animationSpec = tween(durationMillis = 300), label = "TextColorAnimation"
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(24.dp))
                        .background(animatedSegmentColor)
                        .clickable { onOptionSelected(option) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = option,
                        color = animatedTextColor,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

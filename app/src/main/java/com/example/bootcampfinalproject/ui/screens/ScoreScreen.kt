package com.example.bootcampfinalproject.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bootcampfinalproject.ScoreItem
import com.example.bootcampfinalproject.ui.components.TitleThinText

@Composable
fun ScoreScreen(navController: NavController){
    Scaffold { innerPadding->
        Column (
            modifier = Modifier
                .padding(innerPadding)
                .background(color = Color.Black),
        ){
            ScorePageUi(navController, modifier = Modifier, titleFirst = "Skor", titleSecond = "Tablosu")
        }
    }
}

@Composable
fun ScorePageUi(navController: NavController, modifier: Modifier = Modifier, titleFirst: String, titleSecond:String){

    val scoreList = remember { mutableStateOf(arrayListOf(ScoreItem(1, "Kenan", 80))) }

    Column (
        modifier = modifier
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
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
            Spacer(modifier = Modifier.height(30.dp))
        }

        ScoreList(scoreList.value)
}
}

@Composable
fun ScoreList(items: List<ScoreItem>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items.size) { item ->
            ScoreItemUi(scoreItem = items[item])
        }
    }
}

@Composable
fun ScoreItemUi(scoreItem: ScoreItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth().height(70.dp),
    ) {
        Row (
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = scoreItem.scoreId.toString(),
                modifier = Modifier.background(Color.Green)
                    .fillMaxHeight()
                    .width(50.dp),
                textAlign = TextAlign.Center)

            Text(
                text = scoreItem.score.toString(),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}
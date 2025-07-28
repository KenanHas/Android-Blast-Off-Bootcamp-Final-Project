package com.example.bootcampfinalproject.ui.screens.score

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bootcampfinalproject.R
import com.example.bootcampfinalproject.ScoreItem
import com.example.bootcampfinalproject.data.local.database.LocalAppDatabase
import com.example.bootcampfinalproject.ui.components.TopBarDesign
import com.example.bootcampfinalproject.ui.theme.DARKGREEN
import com.example.bootcampfinalproject.ui.theme.LIGHTGREEN
import com.example.bootcampfinalproject.ui.theme.YELLOW
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun ScoreScreen(navController: NavController) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            ScorePageUi(
                navController,
                modifier = Modifier,
                titleFirst = "Skor",
                titleSecond = "Tablosu"
            )
        }
    }
}

@Composable
fun ScorePageUi(
    navController: NavController,
    modifier: Modifier = Modifier,
    titleFirst: String,
    titleSecond: String
) {
    var scoreList = remember { mutableStateOf(emptyList<ScoreItem>()) }
    val db = LocalAppDatabase.current
    val userDao = db.scoreDao()

    val fetchScores: suspend () -> Unit = {
        withContext(Dispatchers.IO) {
            scoreList.value = userDao.getAllScores()
        }
    }

    LaunchedEffect(Unit) {
        fetchScores()
    }

    Column(
        modifier = modifier
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            TopBarDesign(navController, "Skor Tablosu")

            Spacer(modifier = Modifier.height(30.dp))
        }

        ScoreList(scoreList.value, fetchScores)
    }
}

@Composable
fun ScoreList(items: List<ScoreItem>, onScoreDeleted: suspend () -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items.size) { index ->
            ScoreItemUi(scoreItem = items[index], index, onScoreDeleted = onScoreDeleted)
        }
    }
}

@Composable
fun ScoreItemUi(scoreItem: ScoreItem, index: Int, onScoreDeleted: suspend () -> Unit) {

    val db = LocalAppDatabase.current
    val userDao = db.scoreDao()

    val coroutineScope = rememberCoroutineScope()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(top = 10.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(color = LIGHTGREEN)
                .padding(10.dp)
                .border(
                    1.dp,
                    DARKGREEN, shape = CutCornerShape(10.dp)
                )
        ) {
            Text(
                text = (index+1).toString(),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(10.dp)
                    .background(YELLOW, shape = CutCornerShape(50.dp))
                    .fillMaxHeight()
                    .width(50.dp)
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )

            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.padding(bottom = 4.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_person),
                        contentDescription = "person",
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = scoreItem.userName,
                        fontSize = 16.sp, // Adjust text size
                        color = Color.DarkGray
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_score),
                        contentDescription = "score",
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${scoreItem.score} score",
                        fontSize = 14.sp,
                        color = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_timer),
                        contentDescription = "timer",
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${scoreItem.time} sec.",
                        fontSize = 14.sp,
                        color = Color.DarkGray
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = "delete_score",
                    tint = Color.Red,
                    modifier = Modifier
                        .clickable {
                            coroutineScope.launch {
                                withContext(Dispatchers.IO) {
                                    userDao.deleteScoreById(scoreItem.scoreId)
                                    onScoreDeleted()
                                }
                            }
                        }
                        .padding(8.dp)
                )
            }
        }
    }
}
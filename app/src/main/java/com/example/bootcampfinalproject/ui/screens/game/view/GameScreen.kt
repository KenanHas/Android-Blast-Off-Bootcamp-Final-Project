package com.example.bootcampfinalproject.ui.screens.game.view

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bootcampfinalproject.R
import com.example.bootcampfinalproject.data.local.database.LocalAppDatabase
import com.example.bootcampfinalproject.ui.components.TopBarDesign
import com.example.bootcampfinalproject.ui.screens.game.model.CardItem
import com.example.bootcampfinalproject.ui.screens.game.viewModel.MemoryGameViewModel
import com.example.bootcampfinalproject.ui.screens.score.model.ScoreItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun GameScreen(navController: NavController, difficulty: String, userName: String){
    Scaffold { innerPadding->
        Column (
            modifier = Modifier
                .padding(innerPadding)
        ){
            GamePageUi(navController, difficulty, userName = userName)
        }
    }
}

@Composable
fun GamePageUi(navController: NavController, difficulty: String,
               viewModel: MemoryGameViewModel = viewModel(factory = MemoryGameViewModel.Factory(difficulty)),
               userName: String
){
    val score by viewModel.score
    val timeLeft by viewModel.timeLeft
    val totalTime by viewModel.totalTime
    val gameEnded by viewModel.gameEnded
    val gameWon by viewModel.gameWon

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp, vertical = 20.dp)
    ) {

        TopBarDesign(navController, "")

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${stringResource(id = R.string.score)}: $score",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${stringResource(id = R.string.time)}: $timeLeft",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (!gameEnded) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(viewModel.cards) { card ->
                    MemoryCard(card = card, onCardClick = { viewModel.onCardClicked(it) })
                }
            }
        } else {
            GameEndScreen(gameWon = gameWon, score = score, userName = userName, difficulty = difficulty, time = totalTime,onPlayAgain = { viewModel.resetGame() })
        }
    }
}

@Composable
fun MemoryCard(card: CardItem, onCardClick: (CardItem) -> Unit) {
    val rotation by animateFloatAsState(
        targetValue = if (card.isFaceUp) 180f else 0f,
        animationSpec = tween(durationMillis = 300)
    )

    val backgroundColor by animateColorAsState(
        targetValue = when {
            card.isMatched -> MaterialTheme.colorScheme.secondary
            card.isFaceUp -> Color(0xFF505050)
            else -> MaterialTheme.colorScheme.primary
        },
        animationSpec = tween(durationMillis = 300)
    )

    Card(
        modifier = Modifier
            .aspectRatio(1f)
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 8 * density
            }
            .clickable { onCardClick(card) },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (card.isFaceUp || card.isMatched) {
                Text(
                    text = card.number.toString(),
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.graphicsLayer {
                        rotationY = rotation
                    }
                )
            } else {
                Text(
                    text = "?",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.graphicsLayer {
                        rotationY = rotation
                    }
                )
            }
        }
    }
}

@Composable
fun GameEndScreen(gameWon: Boolean, score: Int, userName: String, difficulty: String, time: Int, onPlayAgain: () -> Unit) {

    val db = LocalAppDatabase.current
    val userDao = db.scoreDao()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (gameWon) {
            Text(
                text = stringResource(id = R.string.cong_message),
                color = MaterialTheme.colorScheme.primary,
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${stringResource(id = R.string.your_score)}: $score",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 24.sp
            )
        } else {
            Text(
                text = stringResource(id = R.string.time_finish),
                color = MaterialTheme.colorScheme.primary,
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${stringResource(id = R.string.your_score)}: $score",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 24.sp
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onPlayAgain) {
            Text(text = stringResource(id = R.string.play_again))
        }
        LaunchedEffect(Unit) {
            withContext(Dispatchers.IO) {
                userDao.insertScore(ScoreItem(userName = userName, score = score, time = time, category = difficulty))
            }
        }
    }
}
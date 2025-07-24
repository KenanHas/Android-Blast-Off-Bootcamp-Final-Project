package com.example.bootcampfinalproject

data class GameStateItem(
    val cards: List<CardItem>,
    val score: Int,
    val timeLeft: Int,
    val selectedCards: List<CardItem> // Şu anda açık olan kartlar (en fazla 2)
)
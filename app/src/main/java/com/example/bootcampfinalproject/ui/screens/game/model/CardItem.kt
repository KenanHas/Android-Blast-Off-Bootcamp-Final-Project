package com.example.bootcampfinalproject.ui.screens.game.model

data class CardItem(
    val id: Int,
    val number: Int,
    var isFaceUp: Boolean = false,
    var isMatched: Boolean = false
)
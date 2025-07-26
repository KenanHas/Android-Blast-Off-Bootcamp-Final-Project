package com.example.bootcampfinalproject

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scores")
data class ScoreItem(
    @PrimaryKey(autoGenerate = true)
    val scoreId: Int = 0,
    @ColumnInfo(name = "userName")
    val userName: String,
    @ColumnInfo(name = "score")
    val score: Int,
    @ColumnInfo(name = "time")
    val time: Int,
    @ColumnInfo(name = "category")
    val category: String
)

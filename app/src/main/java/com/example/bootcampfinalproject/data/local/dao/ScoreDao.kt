package com.example.bootcampfinalproject.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.bootcampfinalproject.ScoreItem

@Dao
interface ScoreDao {
    @Query("SELECT * FROM scores")
    fun getAllScores(): List<ScoreItem>

    @Insert
    fun insertScore(score: ScoreItem)

    @Delete
    fun delete(user: ScoreItem)

}
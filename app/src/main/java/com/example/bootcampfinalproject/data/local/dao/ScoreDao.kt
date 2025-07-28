package com.example.bootcampfinalproject.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.bootcampfinalproject.ui.screens.score.model.ScoreItem

@Dao
interface ScoreDao {
    @Query("SELECT * FROM scores")
    fun getAllScores(): List<ScoreItem>

    @Insert
    fun insertScore(score: ScoreItem)

    @Query("DELETE FROM scores WHERE scoreId = :scoreId")
    suspend fun deleteScoreById(scoreId: Int)

    @Query("DELETE FROM scores")
    suspend fun deleteAllScores()

}
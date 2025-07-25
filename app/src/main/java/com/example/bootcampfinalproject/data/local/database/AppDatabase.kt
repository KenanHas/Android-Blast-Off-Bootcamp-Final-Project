package com.example.bootcampfinalproject.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bootcampfinalproject.ScoreItem
import com.example.bootcampfinalproject.data.local.dao.ScoreDao

@Database(entities = [ScoreItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun scoreDao(): ScoreDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: android.content.Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = androidx.room.Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "score_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
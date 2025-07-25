package com.example.bootcampfinalproject.data.local.database

import androidx.compose.runtime.compositionLocalOf

val LocalAppDatabase = compositionLocalOf<AppDatabase> {
    error("No AppDatabase provided")
}
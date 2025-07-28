package com.example.bootcampfinalproject

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.bootcampfinalproject.data.local.database.AppDatabase
import com.example.bootcampfinalproject.data.local.database.LocalAppDatabase
import com.example.bootcampfinalproject.navigation.AppNavigation
import com.example.bootcampfinalproject.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    private val themeViewModel by viewModels<ThemeViewModel>()
    private lateinit var appDatabase: AppDatabase

    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appDatabase = AppDatabase.getDatabase(applicationContext)

        enableEdgeToEdge()
        setContent {
            val isDarkTheme by themeViewModel.isDarkTheme.collectAsStateWithLifecycle()
            MyApplicationTheme(darkTheme = isDarkTheme) {
                CompositionLocalProvider(
                    LocalAppDatabase provides appDatabase
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        AppNavigation(themeViewModel = themeViewModel)
                    }
                }
            }
        }
    }
}
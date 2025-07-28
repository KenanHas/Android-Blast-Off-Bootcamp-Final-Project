package com.example.bootcampfinalproject.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bootcampfinalproject.R
import com.example.bootcampfinalproject.ThemeViewModel
import com.example.bootcampfinalproject.data.local.database.LocalAppDatabase
import com.example.bootcampfinalproject.ui.components.SegmentedSelectionButton
import com.example.bootcampfinalproject.ui.components.StartPageButton
import com.example.bootcampfinalproject.ui.components.TopBarDesign
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun SettingsScreen(navController: NavController, themeViewModel: ThemeViewModel) {

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            SettingsPageUi(
                navController,
                modifier = Modifier,
                title = stringResource(id = R.string.settings_page),
                themeViewModel
            )
        }
    }
}

@Composable
fun SettingsPageUi(
    navController: NavController,
    modifier: Modifier = Modifier,
    title: String,
    themeViewModel: ThemeViewModel
) {
    val selectedMode = remember { mutableStateOf("") }
    selectedMode.value = stringResource(id = R.string.light)
    val lightMode = stringResource(id = R.string.light)

    val db = LocalAppDatabase.current
    val userDao = db.scoreDao()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = modifier) {
            TopBarDesign(navController, title, true)
        }

        Column {
            SegmentedSelectionButton(
                options = listOf(stringResource(R.string.light), stringResource(id = R.string.dark)),
                selectedOption = selectedMode.value,
                onOptionSelected = { newOption ->
                    selectedMode.value = newOption
                    if (selectedMode.value == lightMode)
                        themeViewModel.toggleTheme(false)
                    else
                        themeViewModel.toggleTheme(true)
                },
                modifier = Modifier.fillMaxWidth(0.8f)// Ekranın %80'ini kaplasın
            )
        }

        Column {
            StartPageButton(modifier = Modifier.padding(bottom = 20.dp), action = {
                coroutineScope.launch {
                    withContext(Dispatchers.IO) {
                        userDao.deleteAllScores()
                    }
                }
            }, buttonText = stringResource(id = R.string.delete_all_scores))
            StartPageButton(
                modifier = Modifier.padding(bottom = 20.dp),
                action = { navController.navigateUp() },
                buttonText = stringResource(id = R.string.return_landing_page)
            )
        }
    }
}

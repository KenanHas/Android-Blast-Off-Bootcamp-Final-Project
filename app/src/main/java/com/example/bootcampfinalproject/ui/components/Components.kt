package com.example.bootcampfinalproject.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bootcampfinalproject.ui.theme.YELLOW

@Composable
fun TitleThinText(
    text: String,
    modifier: Modifier = Modifier, // Dışarıdan Modifier uygulamak için
    color: Color,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: TextUnit = 20.sp
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontWeight = fontWeight,
        fontSize = fontSize
    )
}

@Composable
fun StartPageButton(modifier: Modifier = Modifier, action: () -> Unit, buttonText: String) {

    Button(
        onClick = {
            action()
        },
        modifier = modifier
    ) {
        Text(buttonText, color = Color.Green)
    }
}

@Composable
fun TopBarDesign(navController: NavController, title: String, isVisibleButton: Boolean = true) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (isVisibleButton) {
            IconButton(
                onClick = {
                    navController.navigateUp()
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = YELLOW,
                    disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Geri"
                )
            }
        }
        TitleThinText(
            text = title,
            color = YELLOW,
            fontSize = 30.sp
        )

        Spacer(Modifier)
    }
}

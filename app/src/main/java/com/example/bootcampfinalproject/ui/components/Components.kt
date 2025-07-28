package com.example.bootcampfinalproject.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun TitleThinText(
    text: String,
    modifier: Modifier = Modifier, // Dışarıdan Modifier uygulamak için
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: TextUnit = 20.sp
) {
    Text(
        text = text,
        modifier = modifier,
        color = MaterialTheme.colorScheme.primary,
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
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary),
        modifier = modifier
    ) {
        Text(buttonText, color = MaterialTheme.colorScheme.tertiary)
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
                    contentColor = MaterialTheme.colorScheme.tertiary,
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
            fontSize = 30.sp
        )

        Spacer(Modifier)
    }
}

@Composable
fun SegmentedSelectionButton(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val containerColor = MaterialTheme.colorScheme.primary.copy(0.7f)
    val selectedSegmentColor = MaterialTheme.colorScheme.primary
    val unselectedTextColor = MaterialTheme.colorScheme.secondary.copy(0.7f)
    val selectedTextColor = MaterialTheme.colorScheme.secondary

    Box(
        modifier = modifier
            .height(40.dp)
            .offset(y = 10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(containerColor)
            .padding(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            options.forEach { option ->
                val isSelected = option == selectedOption

                val animatedSegmentColor by animateColorAsState(
                    targetValue = if (isSelected) selectedSegmentColor else Color.Transparent,
                    animationSpec = tween(durationMillis = 300), label = "SegmentColorAnimation"
                )

                val animatedTextColor by animateColorAsState(
                    targetValue = if (isSelected) selectedTextColor else unselectedTextColor,
                    animationSpec = tween(durationMillis = 300), label = "TextColorAnimation"
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(24.dp))
                        .background(animatedSegmentColor)
                        .clickable { onOptionSelected(option) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = option,
                        color = animatedTextColor,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}
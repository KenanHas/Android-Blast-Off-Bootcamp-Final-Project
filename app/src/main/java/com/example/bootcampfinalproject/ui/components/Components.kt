package com.example.bootcampfinalproject.ui.components

import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp // sp birimi için

@Composable
fun TitleThinText(
    text: String,
    modifier: Modifier = Modifier, // Dışarıdan Modifier uygulamak için
    color: Color,
    fontWeight: FontWeight = FontWeight.Thin,
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
fun StartPageButton(modifier:Modifier = Modifier, action:()->Unit, buttonText: String){

    Button(
        onClick = {
            action()
        },
        modifier = modifier
    ) {
        Text(buttonText, color = Color.Green)
    }

}

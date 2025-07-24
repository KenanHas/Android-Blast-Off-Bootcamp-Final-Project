package com.example.bootcampfinalproject.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bootcampfinalproject.Screen
import com.example.bootcampfinalproject.ui.components.StartPageButton
import com.example.bootcampfinalproject.ui.components.TitleThinText

@Composable
fun StartScreen(navController: NavController){
    Scaffold { innerPadding->
        Column (modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(color = Color.Black)
        ){
            StartPageUi(navController, modifier = Modifier, "Oyun", "Kur")
        }
    }
}

@Composable
fun StartPageUi(navController: NavController, modifier: Modifier = Modifier, titleFirst: String, titleSecond:String){
    var selectedDifficulty by remember { mutableStateOf("EASY") }
    var userName by remember { mutableStateOf("") }

    Column (
        modifier = modifier
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly // Bu, ana sütundaki doğrudan çocukları arasına boşluk bırakır
    ){
        // 1. Üst Başlık Sütunu
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            TitleThinText(
                text = titleFirst,
                color = Color.Green,
                fontSize = 40.sp
            )

            TitleThinText(
                text = titleSecond,
                modifier = Modifier.offset(y = 7.dp),
                color = Color.Green,
                fontSize = 40.sp
            )
        }

        // 2. Orta İçerik Sütunu (TextField ve SegmentedButton)
        // Bu sütun artık fillMaxSize() kullanmıyor, sadece içeriği kadar yer kaplayacak.
        Column (
            Modifier.weight(1f),
            verticalArrangement = Arrangement.Center, // Kendi içindeki elemanları dikeyde ortalar
            horizontalAlignment = Alignment.CenterHorizontally // Kendi içindeki elemanları yatayda ortalar
        ){
            TextField(
                value = userName,
                onValueChange = { value ->
                    userName = value

                },
                modifier = Modifier.fillMaxWidth(0.8f), // SegmentedButton ile aynı genişlikte olsun
                shape = CutCornerShape(18.dp),
                singleLine = true,
                label = {
                    Text(
                        text = "Kullanıcı Adı"
                    )
                }
            )

            Spacer(modifier = Modifier.height(24.dp)) // TextField ile SegmentedButton arasına boşluk

            SegmentedSelectionButton(
                options = listOf("EASY", "HARD"),
                selectedOption = selectedDifficulty,
                onOptionSelected = { newOption ->
                    selectedDifficulty = newOption
                },
                modifier = Modifier.fillMaxWidth(0.8f) // Ekranın %80'ini kaplasın
            )
        }

        // 3. Alt Buton Sütunu
        // Bu sütun kalan tüm dikey alanı kaplayacak ve içindeki butonu en alta hizalayacak.
        Column (
            modifier = Modifier
                .fillMaxWidth() // Yatayda tam genişlik kaplasın
                .weight(1f), // Kalan tüm dikey alanı kapla
            verticalArrangement = Arrangement.Bottom, // Kendi içindeki butonu en alta hizala
            horizontalAlignment = Alignment.CenterHorizontally // Butonu yatayda ortala
        ){
            // Butonun kendi iç modifier'ı, eğer StartPageButton içinde fillMaxWidth yoksa eklenmeli
            StartPageButton(modifier = Modifier.fillMaxWidth(0.8f), action = {
                navController.navigate(Screen.Game.createRoute(selectedDifficulty))
            }, buttonText = "Başla")
        }
    }
}

@Composable
fun SegmentedSelectionButton(
    options: List<String>, // "EASY", "HARD" gibi seçenekler
    selectedOption: String, // Şu an seçili olan seçenek
    onOptionSelected: (String) -> Unit, // Seçim değiştiğinde çağrılacak callback
    modifier: Modifier = Modifier
) {
    // Dış kutunun yuvarlak köşeleri ve arka plan rengi
    val containerColor = Color(0xFF4CAF50) // Yeşil tonu, görseldeki gibi
    val selectedSegmentColor = Color.DarkGray// Seçili segmentin daha açık yeşil tonu
    val unselectedTextColor = Color.White.copy(alpha = 0.7f) // Seçili olmayan metin rengi
    val selectedTextColor = Color.White // Seçili metin rengi

    Box(
        modifier = modifier
            .height(40.dp) // Butonun yüksekliği
            .offset(y = 10.dp)
            .clip(RoundedCornerShape(20.dp)) // Yuvarlak köşeler
            .background(containerColor)
            .padding(4.dp) // İçerik ile kenar arasında boşluk
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            options.forEach { option ->
                val isSelected = option == selectedOption

                // Seçili segmentin arka plan rengi animasyonu
                val animatedSegmentColor by animateColorAsState(
                    targetValue = if (isSelected) selectedSegmentColor else Color.Transparent,
                    animationSpec = tween(durationMillis = 300), label = "SegmentColorAnimation"
                )

                // Metin rengi animasyonu
                val animatedTextColor by animateColorAsState(
                    targetValue = if (isSelected) selectedTextColor else unselectedTextColor,
                    animationSpec = tween(durationMillis = 300), label = "TextColorAnimation"
                )

                Box(
                    modifier = Modifier
                        .weight(1f) // Her seçenek eşit genişlikte yer kaplar
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(24.dp)) // İç segmentlerin yuvarlak köşeleri
                        .background(animatedSegmentColor)
                        .clickable { onOptionSelected(option) }, // Tıklama olayı
                    contentAlignment = Alignment.Center // Metni ortala
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

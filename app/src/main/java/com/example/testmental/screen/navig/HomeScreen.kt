package com.example.testmental.screen.navig

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)) // светло-серый фон
    ) {
        // Заголовок с месяцем и стрелками
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
            Text(
                text = "Июнь 2025",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Вперёд")
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(historyItems) { item ->
                MoodHistoryItem(item)
            }
        }
    }
}

// Данные одной записи истории
@Composable
fun MoodHistoryItem(item: MoodEntry) {
    val bgColor = when (item.color) {
        "blue" -> Color(0xFFE5F0FD)
        "green" -> Color(0xFFE7F6EC)
        "yellow" -> Color(0xFFFFF9E5)
        else -> Color.White
    }
    val iconColor = when (item.color) {
        "blue" -> Color(0xFF409CFF)
        "green" -> Color(0xFF4CAF50)
        "yellow" -> Color(0xFFF9B100)
        else -> Color.Gray
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(bgColor, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Text(text = item.date, color = iconColor, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = item.mood,
            color = iconColor,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            item.activities.forEach {
                ActivityIcon(it, iconColor)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item.emotions.forEach {
                EmotionChip(text = it, color = iconColor.copy(alpha = 0.2f), textColor = iconColor)
            }
        }
    }
}

@Composable
fun ActivityIcon(name: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = Icons.Default.Star, contentDescription = name, tint = color)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = name, color = color, fontSize = 14.sp)
    }
}

@Composable
fun EmotionChip(text: String, color: Color, textColor: Color) {
    Box(
        modifier = Modifier
            .background(color, RoundedCornerShape(16.dp))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text = text, color = textColor, fontSize = 14.sp)
    }
}

// Модель данных

data class MoodEntry(
    val date: String, // пример: "СЕГОДНЯ, 27 ИЮЛЯ, ДОМ"
    val mood: String, // пример: "Отлично"
    val activities: List<String>, // пример: ["Семья", "Отдых", "Покупки"]
    val emotions: List<String>, // пример: ["Счастье", "Радость"]
    val color: String // "blue", "green", "yellow"
)

val historyItems = listOf(
    MoodEntry(
        date = "СЕГОДНЯ, 27 ИЮЛЯ, ДОМ",
        mood = "Отлично",
        activities = listOf("Семья", "Отдых", "Покупки"),
        emotions = listOf("Счастье", "Радость", "Облегчение", "Умиротворённость", "Благодарность"),
        color = "blue"
    ),
    MoodEntry(
        date = "ВТОРНИК, 26 ИЮЛЯ, ПОЕЗДКА",
        mood = "Хорошо",
        activities = listOf("Отдых", "Игры"),
        emotions = listOf("Радость", "Умиротворённость", "Надежда", "Гордость", "Энтузиазм"),
        color = "green"
    ),
    MoodEntry(
        date = "СРЕДА, 25 ИЮЛЯ, ДОМ",
        mood = "Не плохо",
        activities = listOf("Отдых", "Уборка", "Чтение"),
        emotions = listOf("Беспокойство", "Подавленность"),
        color = "yellow"
    )
)

package com.example.testmental.ui.dashboard.home


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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testmental.domain.model.SelfCare
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val currentDate = LocalDate.now()
    val month = currentDate.month.getDisplayName(TextStyle.FULL, Locale("ru"))
    val year = currentDate.year
    val entries by viewModel.selfCareList.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
            Text(
                text = "$month $year".replaceFirstChar { it.uppercase() },
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Вперёд")
        }

        Spacer(modifier = Modifier.height(8.dp))

        entries.forEach {
            MoodHistoryItem(it)
        }
    }
}


@Composable
fun MoodHistoryItem(item: SelfCare) {
    val iconColor = when (item.mood) {
        "Отлично" -> Color(0xFF409CFF)
        "Хорошо" -> Color(0xFF4CAF50)
        "Средне", "Не плохо" -> Color(0xFFF9B100)
        else -> Color.Gray
    }
    val bgColor = iconColor.copy(alpha = 0.1f)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(bgColor, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Text(text = item.date, color = iconColor, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = item.mood, color = iconColor, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            item.activities.forEach {
                ActivityIcon(it, iconColor)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            item.emotions.forEach {
                EmotionChip(it, iconColor.copy(alpha = 0.2f), iconColor)
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

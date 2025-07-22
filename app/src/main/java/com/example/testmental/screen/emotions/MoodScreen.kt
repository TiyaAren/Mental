package com.example.testmental.screen.emotions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SentimentDissatisfied
import androidx.compose.material.icons.filled.SentimentNeutral
import androidx.compose.material.icons.filled.SentimentSatisfied
import androidx.compose.material.icons.filled.SentimentVeryDissatisfied
import androidx.compose.material.icons.filled.SentimentVerySatisfied
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testmental.ui.theme.ColorMoodAverage
import com.example.testmental.ui.theme.ColorMoodExcellent
import com.example.testmental.ui.theme.ColorMoodFair
import com.example.testmental.ui.theme.ColorMoodGood
import com.example.testmental.ui.theme.ColorMoodPoor

@Composable
fun MoodScreen() {
    val moods = listOf(
        Mood("Отлично", Icons.Default.SentimentVerySatisfied, ColorMoodExcellent),
        Mood("Хорошо", Icons.Default.SentimentSatisfied, ColorMoodGood),
        Mood("Средне", Icons.Default.SentimentNeutral, ColorMoodAverage), // Жёлтый
        Mood("Так себе", Icons.Default.SentimentDissatisfied, ColorMoodFair), // Коричневый
        Mood("Плохо", Icons.Default.SentimentVeryDissatisfied, ColorMoodPoor),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)

    ) {
        Spacer(modifier = Modifier.height(70.dp))

        Text(
            text = "Добрый день!",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
//                .padding(bottom = 8.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Как ваше самочувствие?",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 24.dp)
                .align(Alignment.CenterHorizontally)

        )

        moods.forEachIndexed { index, mood ->
            MoodItem(mood)
            HorizontalDivider(thickness = 1.dp, color = mood.color.copy(alpha = 0.5f))

        }

//        Spacer(modifier = Modifier.height(32.dp))

        Column(

            modifier = Modifier
                .fillMaxSize(),

            verticalArrangement = Arrangement.Bottom

        ) {
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { /* TODO */ },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(30.dp)
            ) {
                Text("Продолжить")
            }
        }
    }
}


@Composable
fun MoodItem(mood: Mood) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 32.dp)
            .fillMaxWidth()

    ) {
        Icon(
            imageVector = mood.icon,
            contentDescription = mood.label,
            tint = mood.color,
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            fontSize = 20.sp,
            text = mood.label,
            color = mood.color,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

data class Mood(
    val label: String,
    val icon: ImageVector,
    val color: Color
)

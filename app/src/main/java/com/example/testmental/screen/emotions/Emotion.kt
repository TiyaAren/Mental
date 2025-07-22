package com.example.testmental.screen.emotions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.testmental.ui.theme.ColorBackground
import com.example.testmental.ui.theme.ColorButtonEmotions

@Composable
fun Emotion() {
    val emotions = listOf(
        "Восторг",
        "Радость",
        "Эйфория",
        "Вдохновение",
        "Счастье",
        "Уверенность",
        "Благодарность",
        "Удовольствие",
        "Интерес",
        "Надежда",
        "Легкость",
        "Принятие",
        "Спокойствие",
        "Симпатия",
        "Скука",
        "Ожидание",
        "Равнодушие",
        "Усталость",
        "Сомнение",
        "Отстранённость",
        "Тревога",
        "Раздражение",
        "Неловкость",
        "Стыд",
        "Вина",
        "Напряжение",
        "Огорчение",
        "Страх",
        "Грусть",
        "Злость",
        "Зависть",
        "Обида",
        "Отчаяние",
        "Безысходность",
        "Ужас",
        "Паника",
        "Апатия",
        "Пустота",
        "Отвращение",
        "Ненависть",
        "Оцепенение",
        "Унижение",
        "Истощение",
        "Обречённость"
    )

    val selectedEmotions = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .background(ColorBackground)
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState())

    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(ColorButtonEmotions)
                .padding(20.dp)
        ) {
            Column(verticalArrangement = Arrangement.Bottom) {
                Text(
                    text = "Как ты себя чувствуешь?",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    emotions.forEach { emotion ->
                        FilterChip(
                            selected = selectedEmotions.contains(emotion),
                            onClick = {
                                if (selectedEmotions.contains(emotion)) {
                                    selectedEmotions.remove(emotion)
                                } else {
                                    selectedEmotions.add(emotion)
                                }
                            },
                            label = { Text(emotion) },

                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                if (selectedEmotions.isNotEmpty()) {
                    Text(
                        text = "Ты выбрал: ${selectedEmotions.joinToString()}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { /* обработка выбранных эмоций */ },
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Text("Продолжить")
        }

    }
}
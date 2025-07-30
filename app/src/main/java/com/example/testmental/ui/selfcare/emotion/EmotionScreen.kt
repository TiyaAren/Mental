package com.example.testmental.ui.selfcare.emotion

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.testmental.ui.navig.SurveyViewModel
import com.example.testmental.ui.theme.ColorBackground
import com.example.testmental.ui.theme.ColorButtonEmotions
import com.example.testmental.ui.theme.ColorTextPrimaryVariant
import com.example.testmental.ui.theme.ColorTextSecondaryVariant
import com.example.testmental.ui.theme.ColorWindForecast

@Composable
fun EmotionScreen(navController: NavController, moodViewModel: SurveyViewModel) {
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
        Row(
            verticalAlignment = Alignment.CenterVertically // Выравнивание по центру вертикально
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ChevronLeft,
                    contentDescription = "Назад",
                    modifier = Modifier.size(28.dp)
                )
            }

            Text(
                text = "Ваши отметки эмоций",
                style = MaterialTheme.typography.titleMedium, // стиль для заголовка (можно поменять)
                modifier = Modifier.padding(start = 8.dp) // отступ от иконки
            )
        }
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(ColorWindForecast)
                .padding(20.dp)
        ) {
            Column(verticalArrangement = Arrangement.Bottom) {
                Text(
                    text = "Как ты себя чувствуешь?",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 14.dp)
                )

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    emotions.forEach { emotion ->
                        val isSelected = selectedEmotions.contains(emotion)

                        FilterChip(
                            selected = isSelected,
                            onClick = {
                                if (isSelected) {
                                    selectedEmotions.remove(emotion)
                                } else {
                                    selectedEmotions.add(emotion)
                                }
                            },
                            label = { Text(emotion) },
                            colors = FilterChipDefaults.filterChipColors(
                                containerColor = ColorButtonEmotions, // светло-голубой
                                selectedContainerColor = Color(0xFF2196F3), // насыщенно-голубой
                                labelColor = ColorTextPrimaryVariant,
                                selectedLabelColor = ColorTextSecondaryVariant
                            ),
                            border = FilterChipDefaults.filterChipBorder(
                                selected = isSelected,
                                enabled = true, // чип активен
                                borderColor = Color.Transparent,
                                selectedBorderColor = Color.Transparent,
                                disabledBorderColor = Color.Transparent
                            ),
                            shape = MaterialTheme.shapes.small
                        )
                    }
                }


//                Spacer(modifier = Modifier.height(24.dp))

                if (selectedEmotions.isNotEmpty()) {
                    Text(
                        modifier = Modifier.padding(top = 10.dp),
                        text = "Ты выбрал(а): ${selectedEmotions.joinToString()}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        Button(

            onClick = {

                navController.navigate("activity")
            },
            enabled = selectedEmotions.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Text("Продолжить")
        }

    }
}
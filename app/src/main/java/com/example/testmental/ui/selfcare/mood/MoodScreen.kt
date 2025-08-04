package com.example.testmental.ui.selfcare.mood

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.SentimentDissatisfied
import androidx.compose.material.icons.filled.SentimentNeutral
import androidx.compose.material.icons.filled.SentimentSatisfied
import androidx.compose.material.icons.filled.SentimentVeryDissatisfied
import androidx.compose.material.icons.filled.SentimentVerySatisfied
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.testmental.R
import com.example.testmental.clickable
import com.example.testmental.ui.navig.SurveyViewModel
import com.example.testmental.ui.navig.model.MoodUiModel
import com.example.testmental.ui.theme.ColorBackground
import com.example.testmental.ui.theme.ColorMoodAverage
import com.example.testmental.ui.theme.ColorMoodExcellent
import com.example.testmental.ui.theme.ColorMoodFair
import com.example.testmental.ui.theme.ColorMoodGood
import com.example.testmental.ui.theme.ColorMoodPoor
import com.example.testmental.ui.theme.ColorMoodTerrible
import com.example.testmental.ui.theme.ColorTextPrimary05
import com.example.testmental.ui.theme.ColorWindForecast
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun MoodScreen(navController: NavController, moodViewModel: SurveyViewModel, selectedDate: String? = null) {
    val moodUiModels = listOf(
        MoodUiModel("Отлично", Icons.Default.SentimentVerySatisfied, color = ColorMoodExcellent),
        MoodUiModel("Хорошо", Icons.Default.SentimentSatisfied, color = ColorMoodGood),
        MoodUiModel("Средне", Icons.Default.SentimentNeutral, color = ColorMoodAverage), // Жёлтый
        MoodUiModel("Так себе", Icons.Default.SentimentDissatisfied, color = ColorMoodFair), // Коричневый
        MoodUiModel("Плохо", Icons.Default.SentimentVeryDissatisfied, color = ColorMoodPoor),
        MoodUiModel("Ужасно", drawableRes = R.drawable.ic_terrible, color = ColorMoodTerrible)
    )
    val formatter = remember { DateTimeFormatter.ofPattern("yyyy-MM-dd") }
    val dateToDisplay = selectedDate ?: LocalDate.now().format(formatter)

    val selectedItem = remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .background(ColorBackground)
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState())

    ) {
        Row(

            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {  navController.navigate("main")}) {
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
                .weight(8f)
        ) {

            Column(verticalArrangement = Arrangement.Bottom) {
                Text(text = "Дата: $dateToDisplay")

                Text(
                    text = "Добрый день!",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "Как ваше самочувствие?",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(bottom = 14.dp)
                        .align(Alignment.CenterHorizontally)

                )

                moodUiModels.forEach { mood ->
                    val isSelected = selectedItem.value == mood.label
                    MoodItem(
                        moodUiModel = mood,
                        isSelected = isSelected,
                        onClick = { selectedItem.value = mood.label } // Выбор одного элемента
                    )
                    HorizontalDivider(thickness = 1.dp, color = mood.color.copy(alpha = 0.5f))
                }

            }
        }
        Button(
            onClick = {
                selectedItem.value?.let { label ->
                    val mood = moodUiModels.find { it.label == label }
                    mood?.let { moodViewModel.selectMood(it) }
                }
                navController.navigate("emotion")
            },
            enabled = selectedItem.value != null,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(30.dp)
        ) {
            Text("Продолжить")
        }

    }
}


@Composable
fun MoodItem(
    moodUiModel: MoodUiModel,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(if (isSelected) ColorTextPrimary05 else Color.Transparent) // Без фона по умолчанию
            .padding(vertical = 22.dp)
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        if (moodUiModel.imageVector != null) {
            Icon(
                imageVector = moodUiModel.imageVector,
                contentDescription = moodUiModel.label,
                tint = moodUiModel.color,
                modifier = Modifier.size(32.dp)
            )
        } else if (moodUiModel.drawableRes != null) {
            Icon(
                painter = painterResource(id = moodUiModel.drawableRes),
                contentDescription = moodUiModel.label,
                tint = moodUiModel.color,
                modifier = Modifier.size(32.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = moodUiModel.label,
            fontSize = 20.sp,
            color = moodUiModel.color,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}





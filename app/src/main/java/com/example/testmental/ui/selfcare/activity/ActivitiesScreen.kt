package com.example.testmental.ui.selfcare.activity

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.CleaningServices
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.NaturePeople
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.testmental.ui.navig.SurveyViewModel
import com.example.testmental.ui.theme.ColorBackground
import com.example.testmental.ui.theme.ColorButtonEmotions
import com.example.testmental.ui.theme.ColorButtonEmotionsOnClick
import com.example.testmental.ui.theme.ColorWindForecast
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun ActivitiesScreen(navController: NavController, moodViewModel: SurveyViewModel) {
    val activities = listOf(
        ActivityItem("Семья", Icons.Default.Home),
        ActivityItem("Работа", Icons.Default.Work), // Новая иконка
        ActivityItem("Друзья", Icons.Default.Group),
        ActivityItem("Свидания", Icons.Default.Favorite),
        ActivityItem("Спорт", Icons.AutoMirrored.Filled.DirectionsRun),
        ActivityItem("Отдых", Icons.Default.NaturePeople),
        ActivityItem("Кино", Icons.Default.PlayCircleFilled),
        ActivityItem("Чтение", Icons.AutoMirrored.Filled.MenuBook),
        ActivityItem("Игры", Icons.Default.SportsEsports),
        ActivityItem("Уборка", Icons.Default.CleaningServices),
        ActivityItem("Лечь Рано", Icons.Default.Bedtime),
        ActivityItem("Покупки", Icons.Default.ShoppingBag),
        ActivityItem("Добавить", Icons.Default.Add)
    )

    val selectedItems = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBackground)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .verticalScroll(rememberScrollState())

    ) {
        // Назад + заголовок
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon( imageVector = Icons.Default.ChevronLeft,
                    contentDescription = "Назад",
                    modifier = Modifier.size(28.dp))
            }
            Text(
                text = "Ваши отметки занятий",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(ColorWindForecast)
                .padding(24.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Чем вы занимались ?",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                // Сетка
                FlowRow(
                    mainAxisSpacing = 14.dp,
                    crossAxisSpacing = 14.dp,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    activities.forEach { item ->
                        val isSelected = selectedItems.contains(item.label)

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,

                            ) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label,
                                tint = Color.Black, modifier = Modifier

                                    .clip(RoundedCornerShape(50))
                                    .background(
                                        if (isSelected) ColorButtonEmotionsOnClick
                                        else ColorButtonEmotions
                                    )
                                    .padding(12.dp)
                                    .clickable {
                                        if (isSelected) selectedItems.remove(item.label)
                                        else selectedItems.add(item.label)
                                    }

                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = item.label,
                                fontSize = 12.sp,
                                color = Color.Black
                            )
                        }
                    }
                }
                if (selectedItems.isNotEmpty()) {
                    Text(
                        modifier = Modifier.padding(top = 10.dp),
                        text = "Ты выбрал(а): ${selectedItems.joinToString()}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                navController.navigate("main")
            },
            enabled = selectedItems.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        ) {
            Text("Подвести итог")
        }
    }
}

data class ActivityItem(val label: String, val icon: ImageVector)

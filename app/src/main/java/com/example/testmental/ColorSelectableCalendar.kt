package com.example.testmental

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color // <-- ВАЖНО: правильный Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.compose.CalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import java.time.DayOfWeek
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale



@Composable
fun ColorSelectableCalendar() {
    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(12) }
    val endMonth = remember { currentMonth.plusMonths(12) }
    val daysOfWeek = remember { daysOfWeek() }
    val calendarState = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = daysOfWeek.first()
    )
    val dayColors = remember { mutableStateMapOf<CalendarDay, Color>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Выделить определённые дни:", fontWeight = FontWeight.Bold, fontSize = 18.sp)

        Spacer(modifier = Modifier.height(8.dp))

        // Кнопки для раскраски определённых дней
        Row {
            Button(onClick = {
                val day = findDayByDayOfMonth(calendarState, 5)
                day?.let { dayColors[it] = Color.Red }
            }) {
                Text("5 → Красный")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {
                val day = findDayByDayOfMonth(calendarState, 10)
                day?.let { dayColors[it] = Color.Green }
            }) {
                Text("10 → Зелёный")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {
                val day = findDayByDayOfMonth(calendarState, 20)
                day?.let { dayColors[it] = Color.Blue }
            }) {
                Text("20 → Синий")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Календарь
        HorizontalCalendar(
            state = calendarState,
            monthHeader = { MonthHeader(daysOfWeek) },
            dayContent = { day ->
                ColoredDay(day = day, colorMap = dayColors)
            }
        )
    }
}
@Composable
fun ColoredDay(day: CalendarDay, colorMap: Map<CalendarDay, Color>) {
    val bgColor = colorMap[day] ?: Color.Transparent

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(4.dp)
            .clip(CircleShape)
            .background(bgColor),
        contentAlignment = Alignment.Center
    ) {
        val textColor = when (day.position) {
            DayPosition.MonthDate -> if (bgColor != Color.Transparent) Color.White else Color.Black
            else -> Color.LightGray
        }

        Text(
            text = day.date.dayOfMonth.toString(),
            color = textColor,
            fontSize = 14.sp
        )
    }
}
@Composable
fun MonthHeader(daysOfWeek: List<DayOfWeek>) {
    Row(modifier = Modifier.fillMaxWidth()) {
        for (dayOfWeek in daysOfWeek) {
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
            )
        }
    }
}
fun findDayByDayOfMonth(state: CalendarState, dayOfMonth: Int): CalendarDay? {
    return state.firstVisibleMonth.weekDays
        .flatten()
        .firstOrNull { it.date.dayOfMonth == dayOfMonth && it.position == DayPosition.MonthDate }
}

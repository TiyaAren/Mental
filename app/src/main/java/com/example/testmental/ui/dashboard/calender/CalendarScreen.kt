package com.example.testmental.ui.dashboard.calender

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.testmental.rememberFirstMostVisibleMonth
import com.kizitonwose.calendar.compose.CalendarState
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun CalendarScreen(navController: NavHostController) {
    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(24) }
    val endMonth = remember { currentMonth.plusMonths(0) }
    val daysOfWeek = remember { daysOfWeek() }
    val calendarState = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = daysOfWeek.first()
    )
    val dayColors = remember { mutableStateMapOf<CalendarDay, Color>() }
    val todayDate = remember { LocalDate.now() }
    val visibleMonth = rememberFirstMostVisibleMonth(calendarState, 90f)

    val monthLabel = remember(visibleMonth.yearMonth) {
        val month = visibleMonth.yearMonth.month.getDisplayName(TextStyle.FULL, Locale("ru"))
        val year = visibleMonth.yearMonth.year
        "$month $year".replaceFirstChar { it.uppercase() }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = monthLabel,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
        )

        HorizontalCalendar(
            state = calendarState,
            monthHeader = { MonthHeader(daysOfWeek) },
            dayContent = { day ->
                ColoredDay(day = day, colorMap = dayColors) {
                    navController.navigate("mood/${day.date}")
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val todayDay = calendarState.firstVisibleMonth.weekDays
                .flatten()
                .firstOrNull { it.date == todayDate && it.position == DayPosition.MonthDate }

            todayDay?.let {
                dayColors[it] = Color(0xFFE52990) // Фиолетовый
            }
        }) {
            Text("Отметить сегодня")
        }

        Spacer(modifier = Modifier.height(12.dp))

//        Row {
//            Button(onClick = {
//                val day = findDayByDayOfMonth(calendarState, 5)
//                day?.let { dayColors[it] = Color.Red }
//            }) {
//                Text("5 → Красный")
//            }
//
//            Button(onClick = {
//                val day = findDayByDayOfMonth(calendarState, 10)
//                day?.let { dayColors[it] = Color.Green }
//            }) {
//                Text("10 → Зелёный")
//            }
//
//            Button(onClick = {
//                val day = findDayByDayOfMonth(calendarState, 20)
//                day?.let { dayColors[it] = Color.Blue }
//            }) {
//                Text("20 → Синий")
//            }
//        }
    }
}

@Composable
fun ColoredDay(day: CalendarDay, colorMap: Map<CalendarDay, Color>, onClick: () -> Unit) {
    val bgColor = colorMap[day] ?: Color.Transparent

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(4.dp)
            .clip(CircleShape)
            .background(bgColor)
            .clickable(enabled = day.position == DayPosition.MonthDate) { onClick() },
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

package com.example.testmental.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.testmental.data.Converters

@Entity(tableName = "selfcare_table")
@TypeConverters(Converters::class)
data class SelfCareEntity(
    @PrimaryKey val id: String,
    val date: String,
    val mood: String,
    val emotions: List<String>,
    val activities: List<String>
)

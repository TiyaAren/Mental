package com.example.testmental.data.mapper


import com.example.testmental.data.model.SelfCareEntity
import com.example.testmental.domain.model.SelfCare

fun SelfCare.toEntity(): SelfCareEntity {
    return SelfCareEntity(
        id = this.id,
        date = this.date,
        mood = this.mood,
        emotions = this.emotions,
        activities = this.activities
    )
}

fun SelfCareEntity.toDomain(): SelfCare {
    return SelfCare(
        id = this.id,
        date = this.date,
        mood = this.mood,
        emotions = this.emotions,
        activities = this.activities
    )
}

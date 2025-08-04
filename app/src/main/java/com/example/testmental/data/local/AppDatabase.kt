package com.example.testmental.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.testmental.data.model.NoteEntity
import com.example.testmental.data.model.SelfCareEntity
import com.example.testmental.data.remote.Converters

@Database(entities = [NoteEntity::class, SelfCareEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun selfCareDao(): SelfCareDao
}

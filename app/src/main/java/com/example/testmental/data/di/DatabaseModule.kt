package com.example.testmental.data.di

import android.content.Context
import androidx.room.Room
import com.example.testmental.data.local.AppDatabase
import com.example.testmental.data.local.NoteDao
import com.example.testmental.data.local.SelfCareDao
import com.example.testmental.data.local.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "appDatabase.db"
        )
            .fallbackToDestructiveMigration(false)  // добавь для разработки, чтобы не было проблем с миграциями
            .build()
    }

    @Provides
    fun provideNoteDao(db: AppDatabase): NoteDao = db.noteDao()

    @Provides
    fun provideSelfCareDao(db: AppDatabase): SelfCareDao = db.selfCareDao()

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()
}

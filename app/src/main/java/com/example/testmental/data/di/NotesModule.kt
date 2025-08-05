//package com.example.testmental.data.di
//
//
//import com.example.testmental.data.repository.impl.NotesRepositoryImpl
//import com.example.testmental.domain.repository.NotesRepository
//import dagger.Binds
//import dagger.Module
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//abstract class NotesModule {
//
//    @Binds
//    @Singleton
//    abstract fun bindNotesRepository(
//        impl: NotesRepositoryImpl
//    ): NotesRepository
//}

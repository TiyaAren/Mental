package com.example.testmental.domain.repository


// domain/repository/UserRepository.kt

import com.example.testmental.domain.model.User

interface UserRepository {
    suspend fun registerUser(user: User)
    suspend fun loginUser(user: User)
    suspend fun getUserByEmail(email: String): User?
}

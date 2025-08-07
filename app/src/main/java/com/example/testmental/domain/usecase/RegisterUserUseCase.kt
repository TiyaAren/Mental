package com.example.testmental.domain.usecase

import com.example.testmental.domain.model.User
import com.example.testmental.domain.repository.UserRepository
import jakarta.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User) {
        repository.registerUser(user)
    }
}
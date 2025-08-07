package com.example.testmental.domain.usecase

import com.example.testmental.domain.model.User
import com.example.testmental.domain.repository.UserRepository

class RegisterUserUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(user: User) {
        repository.registerUser(user)
    }
}

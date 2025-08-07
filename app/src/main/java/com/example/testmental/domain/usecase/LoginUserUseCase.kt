package com.example.testmental.domain.usecase


import com.example.testmental.domain.model.User
import com.example.testmental.domain.repository.UserRepository
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(email: String, password: String): User? {
        val user = repository.getUserByEmail(email)
        return if (user != null && user.password == password) {
            user
        } else {
            null
        }
    }
}

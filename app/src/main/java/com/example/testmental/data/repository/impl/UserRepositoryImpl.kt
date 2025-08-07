package com.example.testmental.data.repository.impl

import com.example.testmental.data.local.UserDao
import com.example.testmental.data.mapper.toDomain
import com.example.testmental.data.mapper.toEntity
import com.example.testmental.data.remote.ApiService
import com.example.testmental.domain.model.User
import com.example.testmental.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dao: UserDao,
    private val apiService: ApiService // <--- добавлено
) : UserRepository {

    override suspend fun registerUser(user: User) {
        dao.insertUser(user.toEntity())       // локальное сохранение
        apiService.registerUser(user)         // отправка на сервер
    }

    override suspend fun getUserByEmail(email: String): User? {
        return dao.getUserByEmail(email)?.toDomain()
    }
}

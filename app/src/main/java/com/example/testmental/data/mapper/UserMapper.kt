package com.example.testmental.data.mapper

import com.example.testmental.data.model.UserEntity
import com.example.testmental.domain.model.User


fun UserEntity.toDomain() = User(id, name, email, password)

fun User.toEntity() = UserEntity(id, name, email, password)

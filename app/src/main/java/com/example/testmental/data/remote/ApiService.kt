package com.example.testmental.data.remote

import com.example.testmental.domain.model.User
import com.example.testmental.domain.model.LoginRequest
import com.example.testmental.domain.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/register")
    suspend fun registerUser(@Body user: User)

    @POST("auth/login")
    suspend fun loginUser(@Body request: LoginRequest): LoginResponse
}

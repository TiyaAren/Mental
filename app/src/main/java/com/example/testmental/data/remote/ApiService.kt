package com.example.testmental.data.remote

// data/remote/ApiService.kt

import com.example.testmental.domain.model.User
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/api/register")
    suspend fun registerUser(@Body user: User)
}

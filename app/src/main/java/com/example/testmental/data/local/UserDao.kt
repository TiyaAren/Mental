package com.example.testmental.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.testmental.data.model.UserEntity

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): UserEntity?
}

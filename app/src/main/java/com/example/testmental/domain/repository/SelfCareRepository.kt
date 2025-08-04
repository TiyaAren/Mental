package com.example.testmental.domain.repository


import com.example.testmental.domain.model.SelfCare
import kotlinx.coroutines.flow.Flow


interface SelfCareRepository {
    suspend fun saveSelfCare(selfCare: SelfCare)
    suspend fun getSelfCareByDate(date: String): SelfCare?
    fun getAllSelfCare(): Flow<List<SelfCare>>
    suspend fun deleteSelfCare(id: String)
}

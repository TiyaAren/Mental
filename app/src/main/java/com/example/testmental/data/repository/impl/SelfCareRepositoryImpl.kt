package com.example.testmental.data.repository.impl

import com.example.testmental.data.local.SelfCareDao
import com.example.testmental.data.mapper.toDomain
import com.example.testmental.data.mapper.toEntity
import com.example.testmental.domain.model.SelfCare
import com.example.testmental.domain.repository.SelfCareRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SelfCareRepositoryImpl @Inject constructor(
    private val dao: SelfCareDao
) : SelfCareRepository {

    override suspend fun saveSelfCare(selfCare: SelfCare) {
        dao.insert(selfCare.toEntity())
    }

    override suspend fun getSelfCareByDate(date: String): SelfCare? {
        return dao.getByDate(date)?.toDomain()
    }

    override fun getAllSelfCare(): Flow<List<SelfCare>> {
        return dao.getAll().map { list -> list.map { it.toDomain() } }
    }

    override suspend fun deleteSelfCare(id: String) {
        dao.delete(id)
    }
}

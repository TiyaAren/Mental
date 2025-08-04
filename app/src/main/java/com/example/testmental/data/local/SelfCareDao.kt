package com.example.testmental.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testmental.data.model.SelfCareEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SelfCareDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(selfCareEntity: SelfCareEntity)

    @Query("SELECT * FROM selfcare_table")
    fun getAll(): Flow<List<SelfCareEntity>>

    @Query("SELECT * FROM selfcare_table WHERE date = :date LIMIT 1")
    suspend fun getByDate(date: String): SelfCareEntity?

    @Query("DELETE FROM selfcare_table WHERE id = :id")
    suspend fun delete(id: String)
}

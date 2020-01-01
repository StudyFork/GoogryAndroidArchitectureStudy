package com.jay.architecturestudy.data.database.dao

import androidx.room.Insert
import androidx.room.Query
import com.jay.architecturestudy.data.database.entity.KinEntity

interface KinDao {
    @Insert
    suspend fun insertAll(movies: List<KinEntity>)

    @Query("SELECT * from kin")
    suspend fun getAll(): List<KinEntity>

    @Query("DELETE from kin")
    suspend fun clearAll()
}
package com.example.androidarchitecture.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidarchitecture.data.response.ImageData
import com.example.androidarchitecture.data.response.KinData

@Dao
interface KinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(kin: List<KinData>)

    @Query("SELECT * from kin")
    suspend fun getAll(): List<KinData>

    @Query("DELETE from kin")
    suspend fun clearAll()
}
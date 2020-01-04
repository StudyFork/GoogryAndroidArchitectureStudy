package com.example.androidarchitecture.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidarchitecture.data.response.ImageData
import com.example.androidarchitecture.data.response.MovieData

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(image: List<ImageData>)

    @Query("SELECT * from image")
    suspend fun getAll(): List<ImageData>

    @Query("DELETE from image")
    suspend fun clearAll()
}
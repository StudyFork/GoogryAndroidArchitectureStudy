package com.jay.architecturestudy.data.database.dao

import androidx.room.Insert
import androidx.room.Query
import com.jay.architecturestudy.data.database.entity.MovieEntity

interface MovieDao {
    @Insert
    suspend fun insertAll(movies: List<MovieEntity>)

    @Query("SELECT * from movie")
    suspend fun getAll(): List<MovieEntity>

    @Query("DELETE from movie")
    suspend fun clearAll()
}
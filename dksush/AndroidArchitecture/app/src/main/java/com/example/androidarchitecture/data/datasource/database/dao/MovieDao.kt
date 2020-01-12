package com.example.androidarchitecture.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.androidarchitecture.data.response.MovieData

@Dao
interface MovieDao {

    @Insert
    suspend fun insertAll(movie: List<MovieData>)

    @Query("SELECT * from movie")
    suspend fun getAll(): List<MovieData>

    @Query("DELETE from movie")
    suspend fun clearAll()
}
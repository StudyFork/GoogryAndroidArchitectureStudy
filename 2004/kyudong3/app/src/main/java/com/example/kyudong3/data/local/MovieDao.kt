package com.example.kyudong3.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie WHERE search_query = :query")
    fun fetchCacheMovieData(query: String): List<MovieLocalEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovieData(movieList: List<MovieLocalEntity>)
}
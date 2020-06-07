package com.kyudong.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
internal interface MovieDao {
    @Query("SELECT * FROM movie WHERE search_query = :query")
    fun fetchCacheMovieData(query: String): List<MovieLocalEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovieData(movieList: List<MovieLocalEntity>)
}
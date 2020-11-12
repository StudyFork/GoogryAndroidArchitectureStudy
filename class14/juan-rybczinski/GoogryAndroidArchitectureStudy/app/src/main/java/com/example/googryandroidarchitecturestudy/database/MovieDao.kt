package com.example.googryandroidarchitecturestudy.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {
    @Query("SELECT * FROM databasemovie WHERE title LIKE :search")
    fun searchMovies(search: String): List<DatabaseMovie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<DatabaseMovie>)
}
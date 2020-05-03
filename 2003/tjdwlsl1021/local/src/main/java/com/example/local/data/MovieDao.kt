package com.example.local.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.local.MovieItem

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieItem>)

    @Query("SELECT * FROM movie WHERE title LIKE '%' || :title || '%'")
    fun getMoviesByTitle(title: String): List<MovieItem>
}
package com.mtjin.local.source.search

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mtjin.local.model.search.Movie


@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<Movie>)

    @Query("SELECT * FROM movie")
    fun getAllMovies(): List<Movie>

    @Query("SELECT * FROM movie WHERE title LIKE '%' || :title || '%'")
    fun getMoviesByTitle(title: String): List<Movie>

    @Query("DELETE FROM movie")
    fun deleteAllMovies()
}
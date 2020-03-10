package com.mtjin.androidarchitecturestudy.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mtjin.androidarchitecturestudy.data.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)

    @Query("SELECT * FROM Movie")
    suspend fun getAllMovies(): List<Movie>

    @Query("SELECT * FROM Movie WHERE title LIKE '%' || :title || '%'")
    suspend fun getMoviesByTitle(title: String): List<Movie>

    @Query("DELETE FROM Movie")
    suspend fun deleteAllMovies()
}
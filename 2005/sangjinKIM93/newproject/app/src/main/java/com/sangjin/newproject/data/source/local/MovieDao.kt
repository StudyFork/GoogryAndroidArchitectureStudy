package com.sangjin.newproject.data.source.local


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sangjin.newproject.data.model.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<Movie>)

    @Query("SELECT * FROM movie_table")
    fun getAllMovies() : List<Movie>

    @Query("SELECT * FROM movie_table WHERE title = :query")
    fun selectMovies(query: String): List<Movie>

    @Query("DELETE FROM movie_table")
    fun deleteAll()

}
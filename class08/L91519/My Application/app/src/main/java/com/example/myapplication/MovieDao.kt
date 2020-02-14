package com.example.myapplication

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.data.model.MovieResult

@Dao
interface MovieDao{

    @Insert
    fun saveMovies(movies: Movie)

    @Query("SELECT * FROM Movies ORDER BY id DESC LIMIT 1")
    fun getRecent(): Movie

    @Query("DELETE FROM Movies")
    fun deleteAllResult()

}
package com.example.myapplication

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao{

    @Insert
    fun saveMovies(vararg movies: Movie)

    @Query("SELECT * FROM Movies")
    fun getResent(): List<Movie>

    @Query("DELETE FROM Movies")
    fun deleteAllResult()
}
package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.data.model.Movie

@Dao
interface MovieDao{

    @Insert
    fun saveMovies(movies: Movie)

    @Query("SELECT * FROM Movies ORDER BY id DESC LIMIT 1")
    fun getRecent(): Movie

    @Query("DELETE FROM Movies")
    fun deleteAllResult()

}
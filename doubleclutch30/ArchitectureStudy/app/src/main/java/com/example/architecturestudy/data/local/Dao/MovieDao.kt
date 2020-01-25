package com.example.architecturestudy.data.local.Dao

import androidx.room.*
import com.example.architecturestudy.data.local.Entity.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getAll() : List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movieItem: List<MovieEntity>)

    @Query("DELETE FROM movie")
    fun deleteAll()
}
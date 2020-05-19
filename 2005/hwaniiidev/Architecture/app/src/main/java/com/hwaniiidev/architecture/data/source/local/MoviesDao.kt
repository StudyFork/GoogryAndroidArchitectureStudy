package com.hwaniiidev.architecture.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hwaniiidev.architecture.model.Item

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies")
    fun getAll(): List<Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movies: Item)

    @Query("DELETE from movies")
    fun deleteAll()
}
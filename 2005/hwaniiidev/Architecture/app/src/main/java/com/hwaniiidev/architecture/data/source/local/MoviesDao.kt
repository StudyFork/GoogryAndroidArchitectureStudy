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

    @Query("DELETE FROM movies")
    fun deleteAll()

    @Query("SELECT * FROM movies WHERE `query` = :keyword")
    fun getCachedData(keyword: String):List<Item>
}
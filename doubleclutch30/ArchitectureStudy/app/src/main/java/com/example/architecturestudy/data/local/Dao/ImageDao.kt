package com.example.architecturestudy.data.local.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.architecturestudy.data.local.Entity.ImageEntity

@Dao
interface ImageDao {
    @Query("SELECT * FROM image")
    fun getAll(): List<ImageEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserAll(blogItem: List<ImageEntity>)

    @Query("DELETE FROM image")
    fun deleteAll()
}
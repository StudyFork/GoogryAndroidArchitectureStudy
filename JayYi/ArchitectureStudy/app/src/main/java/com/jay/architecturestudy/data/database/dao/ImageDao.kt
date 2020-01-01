package com.jay.architecturestudy.data.database.dao

import androidx.room.Insert
import androidx.room.Query
import com.jay.architecturestudy.data.database.entity.ImageEntity
import io.reactivex.Single

interface ImageDao {
    @Insert
    fun insertAll(movies: List<ImageEntity>)

    @Query("SELECT * from image")
    fun getAll(): Single<List<ImageEntity>>

    @Query("DELETE from image")
    fun clearAll()
}
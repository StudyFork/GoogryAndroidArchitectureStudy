package com.jay.architecturestudy.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jay.architecturestudy.data.database.entity.ImageItemEntity
import io.reactivex.Single

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(images: List<ImageItemEntity>)

    @Query("SELECT * from image")
    fun getAll(): Single<List<ImageItemEntity>>

    @Query("DELETE from image")
    fun clearAll()
}
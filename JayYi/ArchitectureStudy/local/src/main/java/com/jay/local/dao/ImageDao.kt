package com.jay.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jay.local.model.ImageEntity
import io.reactivex.Single

@Dao
internal interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(images: List<ImageEntity>)

    @Query("SELECT * from image")
    fun getAll(): Single<List<ImageEntity>>

    @Query("DELETE from image")
    fun clearAll()
}
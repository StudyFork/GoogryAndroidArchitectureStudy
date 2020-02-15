package com.example.architecturestudy.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.architecturestudy.data.local.Entity.ImageEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ImageDao {
    @Query("SELECT * FROM image")
    fun getAll(): Single<List<ImageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(item: List<ImageEntity>): Completable

    @Query("DELETE FROM image")
    fun deleteAll(): Completable
}
package com.example.architecturestudy.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.architecturestudy.data.local.entity.KinEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface KinDao {
    @Query("SELECT * FROM kin")
    fun getAll(): Single<List<KinEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(item: List<KinEntity>): Completable

    @Query("DELETE FROM kin")
    fun deleteAll(): Completable
}
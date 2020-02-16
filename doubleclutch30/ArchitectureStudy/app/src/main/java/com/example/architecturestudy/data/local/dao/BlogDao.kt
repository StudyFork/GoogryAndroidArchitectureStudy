package com.example.architecturestudy.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.architecturestudy.data.local.entity.BlogEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface BlogDao {
    @Query("SELECT * FROM Blog")
    fun getAll(): Single<List<BlogEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(item: List<BlogEntity>): Completable

    @Query("DELETE FROM Blog")
    fun deleteAll(): Completable
}

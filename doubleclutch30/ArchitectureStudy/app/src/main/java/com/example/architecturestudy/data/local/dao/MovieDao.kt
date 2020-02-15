package com.example.architecturestudy.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.architecturestudy.data.local.Entity.MovieEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getAll(): Single<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(item: List<MovieEntity>): Completable

    @Query("DELETE FROM movie")
    fun deleteAll(): Completable
}
package com.jay.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jay.local.model.MovieEntity
import io.reactivex.Single

@Dao
internal interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<MovieEntity>)

    @Query("SELECT * from movie")
    fun getAll(): Single<List<MovieEntity>>

    @Query("DELETE from movie")
    fun clearAll()
}


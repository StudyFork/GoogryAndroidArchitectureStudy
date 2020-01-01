package com.jay.architecturestudy.data.database.dao

import androidx.room.Insert
import androidx.room.Query
import com.jay.architecturestudy.data.database.entity.MovieEntity
import io.reactivex.Single

interface MovieDao {
    @Insert
    fun insertAll(movies: List<MovieEntity>)

    @Query("SELECT * from movie")
    fun getAll(): Single<List<MovieEntity>>

    @Query("DELETE from movie")
    fun clearAll()
}
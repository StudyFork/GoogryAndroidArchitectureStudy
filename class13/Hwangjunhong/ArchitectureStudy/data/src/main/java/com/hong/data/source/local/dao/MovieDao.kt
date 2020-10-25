package com.hong.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.hong.data.source.local.entity.MovieInfo

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie order by id DESC limit 5")
    fun getAll(): List<MovieInfo>

    @Insert(onConflict = REPLACE)
    suspend fun insert(movie: MovieInfo)

}
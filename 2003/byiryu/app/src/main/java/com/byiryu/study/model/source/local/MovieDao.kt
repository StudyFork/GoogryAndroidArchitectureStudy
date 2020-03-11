package com.byiryu.study.model.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.byiryu.study.model.entity.MovieItem

@Dao
interface MovieDao{

    @Insert(onConflict = REPLACE)
    fun insertAll(items : List<MovieItem>)

    @Query("SELECT * FROM Movie")
    fun getAll() : List<MovieItem>

    @Query("DELETE FROM Movie")
    fun deleteAll()

}
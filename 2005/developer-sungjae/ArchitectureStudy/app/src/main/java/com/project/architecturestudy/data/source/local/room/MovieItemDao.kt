package com.project.architecturestudy.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Observable

@Dao
interface MovieItemDao {

    @Query("SELECT * FROM movielist")
    fun getMovieList(): Observable<List<MovieLocalItem>>

    /* import android.arch.persistence.room.OnConflictStrategy.REPLACE */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: MovieLocalItem)

    @Query("DELETE from movielist")
    fun deleteAll()
}
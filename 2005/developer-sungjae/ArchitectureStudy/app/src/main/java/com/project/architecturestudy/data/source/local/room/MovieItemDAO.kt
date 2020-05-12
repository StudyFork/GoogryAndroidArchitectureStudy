package com.project.architecturestudy.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable

@Dao
interface MovieItemDAO {

    @Query("SELECT * FROM movielist")
    fun getMovieList(): Flowable<List<MovieLocal>>

    /* import android.arch.persistence.room.OnConflictStrategy.REPLACE */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: MovieLocal)

    @Query("DELETE from movielist")
    fun deleteAll()
}
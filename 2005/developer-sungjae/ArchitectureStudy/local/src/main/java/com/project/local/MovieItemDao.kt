package com.project.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project.data.model.MovieItem
import com.project.data.model.NaverMovieEntity
import com.project.local.model.MovieLocalItem
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface MovieItemDao {

    @Query("SELECT * FROM movielist")
    fun getMovieList(): Maybe<MovieLocalItem>

    /* import android.arch.persistence.room.OnConflictStrategy.REPLACE */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: MovieLocalItem): Completable

    @Query("DELETE from movielist")
    fun deleteAll()
}
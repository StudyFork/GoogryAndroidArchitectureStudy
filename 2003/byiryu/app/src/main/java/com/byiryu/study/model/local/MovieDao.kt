package com.byiryu.study.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.byiryu.study.model.data.MovieItem
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MovieDao {

    @Insert(onConflict = REPLACE)
    fun insertAll(items: List<MovieItem>): Completable

    @Query("SELECT * FROM movie")
    fun getAll(): Single<List<MovieItem>>

    @Query("DELETE FROM movie")
    fun deleteAll(): Completable

}
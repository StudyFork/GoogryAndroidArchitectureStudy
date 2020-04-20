package com.example.local.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.local.model.Movie
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getAll(): Single<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<Movie>): Completable

    @Query("DELETE FROM movie")
    fun deleteAll(): Completable
}
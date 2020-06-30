package com.example.data.source.local


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.Movie
import io.reactivex.Single

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<Movie>)

    @Query("SELECT * FROM movie_table")
    fun getAllMovies() : Single<List<Movie>>

    @Query("SELECT * FROM movie_table WHERE title LIKE :query")
    fun selectMovies(query: String): Single<List<Movie>>

    @Query("DELETE FROM movie_table")
    fun deleteAll()

}
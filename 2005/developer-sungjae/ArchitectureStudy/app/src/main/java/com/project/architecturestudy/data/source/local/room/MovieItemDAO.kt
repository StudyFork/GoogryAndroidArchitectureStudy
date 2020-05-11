package com.project.architecturestudy.data.source.local.room

import androidx.room.*
import com.project.architecturestudy.data.model.Movie
import io.reactivex.Flowable

@Dao
interface MovieItemDAO {

    @Query("SELECT * FROM movieitem")
    fun getAllMovie(): Flowable<List<Movie.Items>>

    @Query("DELETE FROM movieitem")
    fun clearAll()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg item: MovieItem)

    @Update
    fun update(vararg item: MovieItem)

    @Delete
    fun delete(vararg item: MovieItem)

}
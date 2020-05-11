package com.project.architecturestudy.data.source.local.room

import androidx.room.*
import io.reactivex.Flowable

@Dao
interface MovieItemDAO {

    @Query("SELECT * FROM movieitem")
    fun getAllPerson(): Flowable<ArrayList<MovieItem>>

    @Query("DELETE FROM movieitem")
    fun clearAll()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg person: MovieItem)

    @Update
    fun update(vararg person: MovieItem)

    @Delete
    fun delete(vararg person: MovieItem)

}
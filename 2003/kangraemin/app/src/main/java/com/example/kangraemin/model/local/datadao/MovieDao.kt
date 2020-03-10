package com.example.kangraemin.model.local.datadao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kangraemin.model.local.datamodel.Movie
import io.reactivex.Flowable

@Dao
interface MovieDao {
    @Query("SELECT * from Movie")
    fun getAll(): Flowable<List<Movie>>

    @Insert
    fun insert(vararg movies: Movie)

    @Query("DELETE from Movie")
    fun deleteAll()
}
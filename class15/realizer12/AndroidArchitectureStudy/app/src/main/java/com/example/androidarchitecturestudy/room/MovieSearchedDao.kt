package com.example.androidarchitecturestudy.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieSearchedDao {

    @Query("SELECT searchedQuery FROM searchedQuery ORDER BY idx DESC LIMIT 5")
    fun getSearchedQuery() : List<String>

    @Insert
    fun insertQuery(movieSearchEntity: MovieSearchEntity)

    @Query("DELETE FROM searchedQuery WHERE idx = :idx")
    fun deleteQuery(idx : Int)

}
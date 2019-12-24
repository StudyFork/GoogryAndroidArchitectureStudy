package com.practice.achitecture.myproject.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.practice.achitecture.myproject.data.source.local.model.HistoryOfSearch

@Dao
interface NaverDao {

    @Query("SELECT * FROM historyOfSearch WHERE (category LIKE :category) ORDER BY id DESC LIMIT 1")
    fun getHistoryOfSearchCache(category: String): HistoryOfSearch

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertResultOfSearch(resultOfSearch: HistoryOfSearch)
}
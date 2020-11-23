package com.jay.aas.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jay.aas.model.SearchHistory

@Dao
interface SearchHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchHistory(searchHistory: SearchHistory)

    @Query("SELECT * FROM SearchHistory ORDER BY id DESC LIMIT 5")
    fun getSearchHistories(): List<SearchHistory>

    @Query("DELETE FROM SearchHistory")
    fun clearSearchHistories()

}
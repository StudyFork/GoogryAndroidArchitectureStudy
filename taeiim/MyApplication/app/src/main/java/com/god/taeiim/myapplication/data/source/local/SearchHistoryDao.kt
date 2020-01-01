package com.god.taeiim.myapplication.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.god.taeiim.myapplication.data.SearchHistory

@Dao
interface SearchHistoryDao {

    @Insert
    fun saveSearchHistory(content: SearchHistory)

    @Query("SELECT * FROM SEARCH_HISTORY WHERE (type LIKE :type) ORDER BY id DESC LIMIT 1")
    fun getLastSearchResult(type: String): SearchHistory

}
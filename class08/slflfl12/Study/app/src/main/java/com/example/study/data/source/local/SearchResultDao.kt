package com.example.study.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.study.data.source.local.model.SearchResult

@Dao
interface SearchResultDao {

    @Insert
    fun addSearchResult(searchResult: SearchResult)

    @Query("SELECT * FROM SearchResult ORDER BY id DESC LIMIT 1")
    fun getRecentSearchResult(): SearchResult
}
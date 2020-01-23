package com.example.study.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.study.data.source.local.model.SearchResult

@Dao
interface SearchResultDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSearchResult(searchResult: SearchResult)

    @Query("SELECT * FROM searchresult ORDER BY id DESC LIMIT 1")
    fun getRecentSearchResult(): SearchResult

    @Query("SELECT * FROM searchresult ORDER BY id DESC")
    fun getAllSearchResult(): List<SearchResult>
}
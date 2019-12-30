package com.example.androidstudy.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidstudy.model.data.SearchResultEntity
import io.reactivex.Single

@Dao
interface SearchResultDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchResult(vararg searchResult: SearchResultEntity)

    @Query("SELECT * FROM searchResult WHERE id = (SELECT IFNULL(MAX(id),0) as id FROM searchResult WHERE type=:type)")
    fun getLastSearchResult(type: String): Single<SearchResultEntity>

    @Query("SELECT search FROM searchResult WHERE type=:type")
    fun getSearchWordList(type: String): Single<List<String>>
}
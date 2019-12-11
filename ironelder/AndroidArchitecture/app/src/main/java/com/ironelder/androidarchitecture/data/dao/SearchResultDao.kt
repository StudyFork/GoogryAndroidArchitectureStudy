package com.ironelder.androidarchitecture.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ironelder.androidarchitecture.data.SearchResult
import io.reactivex.Single

@Dao
interface SearchResultDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchResult(vararg searchResult: SearchResult)

    @Query("SELECT * FROM searchResult WHERE id = (SELECT IFNULL(MAX(id),0) as id FROM searchResult WHERE type=:type)")
    fun getLastSearchResult(type: String): Single<SearchResult>

}
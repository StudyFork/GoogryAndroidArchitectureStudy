package com.ironelder.androidarchitecture.data.dao

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ironelder.androidarchitecture.data.SearchResult
import io.reactivex.Single

@Dao
interface SearchResultDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchResult(vararg searchResult:SearchResult)

    @Query("SELECT * FROM searchResult WHERE type = :type")
    fun getSearchResult(type:String): Single<SearchResult>

}
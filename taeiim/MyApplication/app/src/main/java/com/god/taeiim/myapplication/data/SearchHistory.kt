package com.god.taeiim.myapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.god.taeiim.myapplication.api.model.SearchResult

@Entity(tableName = "search_history")
data class SearchHistory(
    @ColumnInfo(name = "result_list") val resultList: List<SearchResult.Item>,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "query") val query: String,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0
)

package com.practice.achitecture.myproject.data.source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.practice.achitecture.myproject.model.SearchedItem

@Entity(tableName = "historyOfSearch")
data class HistoryOfSearch constructor(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "query") val query: String,
    @ColumnInfo(name = "searchedItemList") val searchedItemList: List<SearchedItem>
)
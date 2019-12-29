package com.practice.achitecture.myproject.data.source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.practice.achitecture.myproject.model.SearchedItem

@Entity(tableName = "historyOfSearch")
data class HistoryOfSearch constructor(
    val category: String,
    val query: String,
    val searchedItemList: List<SearchedItem>
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
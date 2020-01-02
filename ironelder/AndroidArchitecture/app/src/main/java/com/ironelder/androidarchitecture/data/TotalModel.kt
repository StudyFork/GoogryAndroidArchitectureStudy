package com.ironelder.androidarchitecture.data

import androidx.databinding.ObservableArrayList
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "searchResult")
data class SearchResult(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    val id: Long?,
    @ColumnInfo(name="type")
    val type:String,
    @ColumnInfo(name="search")
    val search:String,
    @ColumnInfo(name="result")
    val result:String
)

data class TotalModel(
    val display: Int,
    val items: ObservableArrayList<ResultItem>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)

data class ResultItem(
    val actor: String,
    val director: String,
    val image: String,
    val link: String,
    val subtitle: String,
    val title: String,
    val description: String?,
    val author: String
)
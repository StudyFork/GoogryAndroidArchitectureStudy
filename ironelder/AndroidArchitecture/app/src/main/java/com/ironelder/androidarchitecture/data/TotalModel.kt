package com.ironelder.androidarchitecture.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "searchResult")
data class SearchResult(
    @PrimaryKey
    val type:String,
    val search:String,
    val result:String
)

data class TotalModel(
    val display: Int,
    val items: ArrayList<ResultItem>,
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
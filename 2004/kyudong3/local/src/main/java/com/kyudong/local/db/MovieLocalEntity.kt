package com.kyudong.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
internal data class MovieLocalEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "link")
    val link: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "sub_title")
    val subTitle: String,
    @ColumnInfo(name = "publish_date")
    val pubDate: String,
    @ColumnInfo(name = "director")
    val director: String,
    @ColumnInfo(name = "actor")
    val actor: String,
    @ColumnInfo(name = "user_rating")
    val userRating: Double,
    @ColumnInfo(name = "search_query")
    val searchQuery: String = ""
)
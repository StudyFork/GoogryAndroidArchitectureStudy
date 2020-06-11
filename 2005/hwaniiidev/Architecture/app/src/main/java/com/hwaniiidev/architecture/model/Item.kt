package com.hwaniiidev.architecture.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Item(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "query") var query: String,
    @ColumnInfo(name = "actor") var actor: String,
    @ColumnInfo(name = "director") var director: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "link") val link: String,
    @ColumnInfo(name = "pub_date") var pubDate: String,
    @ColumnInfo(name = "subtitle") var subtitle: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "user_rating") var userRating: String
)
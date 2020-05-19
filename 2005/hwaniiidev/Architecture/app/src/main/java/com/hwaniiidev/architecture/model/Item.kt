package com.hwaniiidev.architecture.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Item(
    @PrimaryKey(autoGenerate = true) val id : Int,
    @ColumnInfo(name = "actor")val actor: String,
    @ColumnInfo(name = "director")val director: String,
    @ColumnInfo(name = "image")val image: String,
    @ColumnInfo(name = "link")val link: String,
    @ColumnInfo(name = "pub_date")val pubDate: String,
    @ColumnInfo(name = "subtitle")val subtitle: String,
    @ColumnInfo(name = "title")val title: String,
    @ColumnInfo(name = "user_rating")val userRating: String
)
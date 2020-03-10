package com.example.kangraemin.model.local.datamodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "image") var image: String,
    @ColumnInfo(name = "director") var director: String,
    @ColumnInfo(name = "actor") var actor: String,
    @ColumnInfo(name = "user_rating") var userRating: String,
    @ColumnInfo(name = "pub_data") var pubDate: String
) {
    @PrimaryKey(autoGenerate = true)
    var idx: Int = 0
}
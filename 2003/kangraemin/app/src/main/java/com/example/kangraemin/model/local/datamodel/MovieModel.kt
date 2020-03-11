package com.example.kangraemin.model.local.datamodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "director") val director: String,
    @ColumnInfo(name = "actor") val actor: String,
    @ColumnInfo(name = "user_rating") val userRating: String,
    @ColumnInfo(name = "pub_data") val pubDate: String
) {
    @PrimaryKey(autoGenerate = true)
    val idx: Int = 0
}
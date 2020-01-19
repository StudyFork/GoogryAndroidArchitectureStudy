package com.example.archstudy.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MovieData")
data class MovieData(
    @PrimaryKey(autoGenerate = false) val title : String,
    @ColumnInfo(name = "actor") val actor : String,
    @ColumnInfo(name = "director") val director : String,
    @ColumnInfo(name = "image") val image : String,
    @ColumnInfo(name = "link") val link : String,
    @ColumnInfo(name = "pubDate") val pubDate : String,
    @ColumnInfo(name = "subtitle") val subtitle : String,
    @ColumnInfo(name = "userRating") val userRating : String
)
package com.example.myapplication.data.local

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "actor")
    val actor: String,
    @ColumnInfo(name = "director")
    val director: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "link")
    val link: String,
    @ColumnInfo(name = "pubDate")
    val pubDate: String,
    @ColumnInfo(name = "subtitle")
    val subtitle: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "userRating")
    val userRating: String
)
package com.example.architecturestudy.data.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "Movie")
data class MovieEntity (
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "link")
    val link: String = "",
    @ColumnInfo(name = "image")
    val image: String = "",
    @ColumnInfo(name = "year")
    val year: String = "",
    @ColumnInfo(name = "director")
    val director: String = "",
    @ColumnInfo(name = "actor")
    val actor: String = "",
    @ColumnInfo(name = "rating")
    val rating: Float = 0.0F
)
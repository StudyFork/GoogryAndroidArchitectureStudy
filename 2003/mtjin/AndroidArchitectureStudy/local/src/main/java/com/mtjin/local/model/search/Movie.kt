package com.mtjin.local.model.search

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class Movie(
    val actor: String,
    val director: String,
    val image: String,
    val link: String,
    val pubDate: String,
    val subtitle: String,
    @PrimaryKey(autoGenerate = false)
    val title: String,
    val userRating: String
)
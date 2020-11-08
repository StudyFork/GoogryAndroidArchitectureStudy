package com.jay.aas.model

import androidx.room.Entity

@Entity(primaryKeys = [("title")])
data class Movie(
    var query: String,
    val actor: String,
    val director: String,
    val image: String,
    val link: String,
    val pubDate: String,
    val subtitle: String,
    val title: String,
    val userRating: String
)
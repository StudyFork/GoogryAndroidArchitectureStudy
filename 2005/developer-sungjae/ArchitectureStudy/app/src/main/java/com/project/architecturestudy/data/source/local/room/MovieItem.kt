package com.project.architecturestudy.data.source.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MovieItem(
    @PrimaryKey(autoGenerate = true)
    val title: String,
    val link: String,
    val image: String,
    val subtitle: String,
    val pubDate: String,
    val director: String,
    val actor: String,
    val userRating: Double
)
package com.example.architecturestudy.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.architecturestudy.data.model.MovieItem

@Entity(tableName = "Movie")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String = "",
    val link: String = "",
    val image: String = "",
    val year: String = "",
    val director: String = "",
    val actor: String = "",
    val rating: Float? = 0.0F
) {
    fun toItem() = MovieItem(
        title = title,
        link = link,
        image = image,
        year = year,
        director = director,
        actor = actor,
        rating = rating ?: 0f
    )
}
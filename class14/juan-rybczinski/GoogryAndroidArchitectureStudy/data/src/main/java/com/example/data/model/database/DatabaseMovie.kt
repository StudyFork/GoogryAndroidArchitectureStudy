package com.example.data.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.Movie

@Entity
data class DatabaseMovie(
    @PrimaryKey
    val link: String,
    val title: String,
    val image: String,
    val subtitle: String,
    val pubDate: String,
    val director: String,
    val actor: String,
    val userRating: Float
)

// Database object to domain object
// It can be redundant for now
fun List<DatabaseMovie>.asDomainModel(): List<Movie> {
    return map {
        Movie(
            title = it.title,
            link = it.link,
            image = it.image,
            subtitle = it.subtitle,
            pubDate = it.pubDate,
            director = it.director,
            actor = it.actor,
            userRating = it.userRating
        )
    }
}
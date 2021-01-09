package com.example.data.model.network

import com.example.data.model.database.DatabaseMovie
import com.example.domain.model.Movie

data class NetworkMovie(
    val display: Int,
    val items: List<Movie>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)

// Network result to domain object
fun NetworkMovie.asDomainModel(): List<Movie> {
    return items
}

// Network result to database object
// It can be redundant for now
fun NetworkMovie.asDatabaseModel(): List<DatabaseMovie> {
    return items.map {
        DatabaseMovie(
            link = it.link,
            title = it.title,
            image = it.image,
            subtitle = it.subtitle,
            pubDate = it.pubDate,
            director = it.director,
            actor = it.actor,
            userRating = it.userRating
        )
    }
}
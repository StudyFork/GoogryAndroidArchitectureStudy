package com.example.remote.model.mapper

import com.example.remote.model.Movie
import com.example.data2.model.Movie as DataMovie

fun remoteToData(movies: List<Movie>): List<DataMovie> {
    return movies.toList().map {
        DataMovie(
            it.actor,
            it.director,
            it.image,
            it.link,
            it.pubDate,
            it.subtitle,
            it.title,
            it.userRating
        )
    }
}
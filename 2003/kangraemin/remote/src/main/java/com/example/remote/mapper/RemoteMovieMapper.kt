package com.example.remote.mapper

import com.example.data.model.Movie
import com.example.remote.model.Movies

object RemoteMovieMapper {
    fun remoteMovieToDataMovie(movies: Movies): List<Movie> {
        return movies.items.toList()
            .map {
                Movie(
                    title = it.title,
                    image = it.image,
                    director = it.director,
                    actor = it.actor,
                    userRating = it.userRating,
                    pubDate = it.pubDate
                )
            }
    }
}
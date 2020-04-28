package com.example.local.mapper

import com.example.local.model.Movie as LocalMovie
import com.example.data.model.Movie as DataMovie

object LocalMovieMapper {
    fun localMovieToDataMovie(movies: List<LocalMovie>): List<DataMovie> {
        return movies.toList()
            .map {
                DataMovie(
                    title = it.title,
                    image = it.image,
                    director = it.director,
                    actor = it.actor,
                    userRating = it.userRating,
                    pubDate = it.pubDate
                )
            }
    }

    fun remoteMovieToLocalMovie(movies: List<DataMovie>): List<LocalMovie> {
        return movies
            .map {
                LocalMovie(
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
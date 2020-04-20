package com.example.data.mapper

import com.example.data.model.Movie as dataMovie
import com.example.local.model.Movie as localMovie
import com.example.remote.model.Movies as remoteMovie

object MovieMapper {
    fun localMovieToDataMovie(movies: List<localMovie>): List<dataMovie> {
        return movies.toList()
            .map {
                dataMovie(
                    title = it.title,
                    image = it.image,
                    director = it.director,
                    actor = it.actor,
                    userRating = it.userRating,
                    pubDate = it.pubDate
                )
            }
    }

    fun remoteMovieToDataMovie(movies: remoteMovie): List<dataMovie> {
        return movies.items.toList()
            .map {
                dataMovie(
                    title = it.title,
                    image = it.image,
                    director = it.director,
                    actor = it.actor,
                    userRating = it.userRating,
                    pubDate = it.pubDate
                )
            }
    }

    fun remoteMovieToLocalMovie(movies: remoteMovie): List<localMovie> {
        return movies.items.toList()
            .map {
                localMovie(
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
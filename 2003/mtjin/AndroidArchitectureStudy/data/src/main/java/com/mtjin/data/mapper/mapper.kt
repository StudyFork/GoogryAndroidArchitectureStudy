package com.mtjin.data.mapper

import com.mtjin.data.model.search.Movie
import com.mtjin.local.model.search.Movie as localMovie
import com.mtjin.remote.model.search.Movie as remoteMovie

fun mapperMovieListRemoteToLocal(movies: List<remoteMovie>): List<localMovie> {
    return movies.toList().map {
        localMovie(
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

fun mapperMovieListLocalToData(movies: List<localMovie>): List<Movie> {
    return movies.toList().map {
        Movie(
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

fun mapperMovieListRemoteToData(movies: List<remoteMovie>): List<Movie> {
    return movies.toList().map {
        Movie(
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


package com.mtjin.remote.mapper

import com.mtjin.remote.model.search.Movie
import com.mtjin.data.model.search.Movie as dataMovie

fun mapperMovieListRemoteToData(movies: List<Movie>): List<dataMovie> {
    return movies.toList().map {
        dataMovie(
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
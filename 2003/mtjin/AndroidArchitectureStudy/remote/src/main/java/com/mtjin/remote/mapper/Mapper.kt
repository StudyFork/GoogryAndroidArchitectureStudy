package com.mtjin.remote.mapper

import com.mtjin.remote.model.search.Movie
import com.mtjin.data.model.search.Movie as DataMovie

fun mapperMovieListRemoteToData(movies: List<Movie>): List<DataMovie> {
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
package com.mtjin.local.mapper

import com.mtjin.local.model.search.Movie
import com.mtjin.data.model.search.Movie as DataMovie

fun mapperMovieListLocalToData(movies: List<Movie>): List<DataMovie> {
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

fun mapperMovieListDataToLocal(movies: List<DataMovie>): List<Movie> {
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
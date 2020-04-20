package com.mtjin.data.mapper

import com.mtjin.data.model.search.Movie

fun mapperMovieListRemoteToLocal(movies: List<com.mtjin.remote.model.search.Movie>): List<com.mtjin.local.model.search.Movie> {
    val localMovies: List<com.mtjin.local.model.search.Movie>
    localMovies = movies.map { movie ->
        com.mtjin.local.model.search.Movie(
            movie.actor,
            movie.director,
            movie.image,
            movie.link,
            movie.pubDate,
            movie.subtitle,
            movie.title,
            movie.userRating
        )
    }
    return localMovies
}

fun mapperMovieListLocalToData(movies: List<com.mtjin.local.model.search.Movie>): List<Movie> {
    val dataMovies: List<Movie>
    dataMovies = movies.map { movie ->
        Movie(
            movie.actor,
            movie.director,
            movie.image,
            movie.link,
            movie.pubDate,
            movie.subtitle,
            movie.title,
            movie.userRating
        )
    }
    return dataMovies
}

fun mapperMovieListRemoteToData(movies: List<com.mtjin.remote.model.search.Movie>): List<Movie> {
    val dataMovies: List<Movie>
    dataMovies = movies.map { movie ->
        Movie(
            movie.actor,
            movie.director,
            movie.image,
            movie.link,
            movie.pubDate,
            movie.subtitle,
            movie.title,
            movie.userRating
        )
    }
    return dataMovies
}


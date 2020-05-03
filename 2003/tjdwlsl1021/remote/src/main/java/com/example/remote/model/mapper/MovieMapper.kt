package com.example.remote.model.mapper

import com.example.data2.model.Movie

object MovieMapper : Mapper.MovieMapper {
    override fun remoteToData(movies: List<com.example.remote.model.Movie>): List<Movie> {
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
}
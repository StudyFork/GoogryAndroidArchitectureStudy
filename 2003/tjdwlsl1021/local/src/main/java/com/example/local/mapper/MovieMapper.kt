package com.example.local.mapper

import com.example.data2.model.Movie
import com.example.local.MovieItem


object MovieMapper : Mapper.MovieMapper {

    override fun localToData(items: List<MovieItem>): List<Movie> {
        return items.toList().map {
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

    override fun dataToLocal(items: List<Movie>): List<MovieItem> {
        return items.toList().map {
            MovieItem(
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
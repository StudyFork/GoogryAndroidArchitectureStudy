package com.example.local.mapper

import com.example.data2.model.Movie
import com.example.local.MovieItem


object MovieMapper : Mapper.MovieMapper {

    override fun LocalToData(items: List<MovieItem>): List<Movie> {
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
        val _items = ArrayList<MovieItem>()

        items.forEach { item ->
            _items.add(
                MovieItem(
                    item.actor,
                    item.director,
                    item.image,
                    item.link,
                    item.pubDate,
                    item.subtitle,
                    item.title,
                    item.userRating
                )
            )

        }
        return _items.toList()
    }
}
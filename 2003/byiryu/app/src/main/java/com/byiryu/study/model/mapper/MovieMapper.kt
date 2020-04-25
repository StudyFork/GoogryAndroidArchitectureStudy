package com.byiryu.study.model.mapper

import com.byiryu.data.model.Item
import com.byiryu.study.model.Movie


object MovieMapper: Mapper.MovieMapper {
    override fun dataToView(items: List<Item>) : List<Movie>{
        return items.map {
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
        }.toList()
    }

    override fun viewToData(movies: List<Movie>) : List<Item>{
        return movies.map {
            Item(
                it.actor,
                it.director,
                it.image,
                it.link,
                it.pubDate,
                it.subtitle,
                it.title,
                it.userRating
            )
        }.toList()
    }

}
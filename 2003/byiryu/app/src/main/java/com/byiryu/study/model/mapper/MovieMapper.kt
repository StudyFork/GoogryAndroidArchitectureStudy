package com.byiryu.study.model.mapper

import com.byiryu.data.model.Item
import com.byiryu.study.model.Movie


object MovieMapper: Mapper.MovieMapper {
    override fun dataToView(items: List<Item>) : List<Movie>{
        val items = ArrayList<Movie>()
        items.forEach { item ->
            items.forEach{ item ->
                items.add(
                    Movie(
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
        }
        return items.toList()
    }

    override fun viewToData(movieItems: List<Movie>) : List<Item>{
        val items = ArrayList<Item>()
        movieItems.forEach { item ->
            movieItems.forEach{ item ->
                items.add(
                    Item(
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
        }
        return items.toList()
    }

}
package com.byiryu.local.model.mapper

import com.byiryu.data.model.Item
import com.byiryu.local.model.MovieItem

object MovieMapper: Mapper.MovieMapper{

    override fun localToData(movieItems: List<MovieItem>): List<Item>{
        return movieItems.map {
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

    override fun dataToLocal(items: List<Item>): List<MovieItem>{
        return items.map {
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
        }.toList()
    }


}
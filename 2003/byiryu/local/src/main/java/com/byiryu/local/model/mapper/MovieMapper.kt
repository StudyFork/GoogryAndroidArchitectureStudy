package com.byiryu.local.model.mapper

import com.byiryu.data.model.Item
import com.byiryu.local.model.MovieItem

object MovieMapper: Mapper.MovieMapper{

    override fun localToData(movieItems: List<MovieItem>): List<Item>{
        val items = ArrayList<Item>()
        movieItems.forEach{ item ->
            items.add(Item(
                item.actor,
                item.director,
                item.image,
                item.link,
                item.pubDate,
                item.subtitle,
                item.title,
                item.userRating
            ))

        }
        return items.toList()
    }

    override fun dataToLocal(items: List<Item>): List<MovieItem>{
        val _items = ArrayList<MovieItem>()

        items.forEach{ item ->
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
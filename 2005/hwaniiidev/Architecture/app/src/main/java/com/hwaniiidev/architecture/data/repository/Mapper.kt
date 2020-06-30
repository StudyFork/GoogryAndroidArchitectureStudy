package com.hwaniiidev.architecture.data.repository

import com.hwaniiidev.architecture.model.ItemApp
import com.hwaniiidev.data.model.Item

object Mapper {
    fun changeMovies(items: ArrayList<Item>): List<ItemApp> {
        val itemsApp = ArrayList<ItemApp>()
        items.forEach {
            ItemApp(
                id = it.id,
                actor = it.actor,
                director = it.director,
                image = it.image,
                link = it.link,
                pubDate = it.pubDate,
                subtitle = it.subtitle,
                title = it.title,
                userRating = it.userRating
                ).let { itemsApp.add(it) }
        }
        return itemsApp
    }
}
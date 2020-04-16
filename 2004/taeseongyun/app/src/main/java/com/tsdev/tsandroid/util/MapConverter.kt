package com.tsdev.tsandroid.util

import com.tsdev.tsandroid.data.Item

class MapConverter : AbstractMapConverter<List<Item>, List<Item>>() {
    override fun toMap(params: List<Item>): List<Item> = params.map { itemData ->
        Item(
            itemData.actor.htmlConvert().split("|").map { it.trim() }
                .filter { it != "" }
                .joinToString { it },
            itemData.director.split("|").map { it.trim() }
                .filter { it != "" }
                .joinToString { it },
            itemData.image,
            itemData.link,
            itemData.pubDate,
            itemData.subtitle.htmlConvert().toString(),
            itemData.title.htmlConvert().toString(),
            itemData.userRating
        )
    }
}
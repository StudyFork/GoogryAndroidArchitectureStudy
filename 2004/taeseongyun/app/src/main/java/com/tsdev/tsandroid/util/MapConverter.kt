package com.tsdev.tsandroid.util

import com.tsdev.tsandroid.data.Item

class MapConverter : AbstractMapConverter<List<Item>, List<Item>>() {
    override fun toMap(params: List<Item>): List<Item> = params.map { itemData ->
        itemData.copy(
            actor = itemData.actor.htmlConvert().split("|")
                .map { it.trim() }
                .filter { it != "" }
                .joinToString { it },

            title = itemData.title.htmlConvert().toString(),
            subtitle = itemData.subtitle.htmlConvert().toString(),

            director = itemData.director.split("|")
                .map { it.trim() }
                .filter { it != "" }
                .joinToString { it }
        )
    }
}
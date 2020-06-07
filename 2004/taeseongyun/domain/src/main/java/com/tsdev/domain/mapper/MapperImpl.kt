package com.tsdev.domain.mapper

import com.tsdev.data.source.Item
import com.tsdev.data.source.MovieResponse
import com.tsdev.domain.ext.htmlConvert

internal class MapperImpl : Mapper<MovieResponse, List<Item>> {

    override fun toData(data: MovieResponse): List<Item> {
        return data.items.map { itemData ->
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
}
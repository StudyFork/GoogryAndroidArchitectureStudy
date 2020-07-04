package com.tsdev.domain.mapper

import com.tsdev.domain.ext.htmlConvert
import com.tsdev.domain.model.Item
import com.tsdev.domain.model.MovieResponse

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
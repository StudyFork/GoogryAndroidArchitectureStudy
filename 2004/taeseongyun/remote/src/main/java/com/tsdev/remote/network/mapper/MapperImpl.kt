package com.tsdev.remote.network.mapper

import com.tsdev.data.model.EntryItem
import com.tsdev.remote.model.RemoteItem
import io.reactivex.rxjava3.core.Single

object MapperImpl : Mapper<RemoteItem, EntryItem> {
    override fun toData(data: RemoteItem): EntryItem {
        return EntryItem(
            actor = data.actor.split("|")
                .filter {
                    it.isNotEmpty()
                }
                .joinToString(", "),
            director = data.director.split("|")
                .filter {
                    it.isNotEmpty()
                }
                .joinToString { it },
            image = data.image,
            link = data.link,
            pubDate = data.pubDate,
            subtitle = data.subtitle,
            title = data.title,
            userRating = data.userRating
        )
    }

    override fun fromData(data: EntryItem): RemoteItem {
        return RemoteItem(
            actor = data.actor,
            director = data.director,
            image = data.image,
            link = data.link,
            pubDate = data.pubDate,
            subtitle = data.subtitle,
            title = data.title,
            userRating = data.userRating
        )
    }
}
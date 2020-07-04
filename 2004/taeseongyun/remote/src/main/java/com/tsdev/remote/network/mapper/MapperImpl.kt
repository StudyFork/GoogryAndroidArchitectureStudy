package com.tsdev.remote.network.mapper

import com.tsdev.data.model.EntryItem
import com.tsdev.remote.model.RemoteItem
import io.reactivex.rxjava3.core.Single

object MapperImpl : Mapper<EntryItem, RemoteItem> {
    override fun toData(data: Single<EntryItem>): Single<RemoteItem> {
        return data.map { entry ->
            RemoteItem(
                actor = entry.actor,
                director = entry.director,
                image = entry.image,
                link = entry.link,
                pubDate = entry.pubDate,
                subtitle = entry.subtitle,
                title = entry.title,
                userRating = entry.userRating
            )
        }
    }

    override fun fromData(data: Single<RemoteItem>): Single<EntryItem> {
        return data.map { remote ->
            EntryItem(
                actor = remote.actor,
                director = remote.director,
                image = remote.image,
                link = remote.link,
                pubDate = remote.pubDate,
                subtitle = remote.subtitle,
                title = remote.title,
                userRating = remote.userRating
            )
        }
    }

}
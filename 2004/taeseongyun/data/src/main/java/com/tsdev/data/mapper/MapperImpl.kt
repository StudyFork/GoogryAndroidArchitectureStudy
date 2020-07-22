package com.tsdev.data.mapper

import com.tsdev.data.model.EntryItem
import com.tsdev.domain.model.DomainItem

internal object MapperImpl : Mapper<EntryItem, DomainItem> {
    override fun toData(item: EntryItem): DomainItem {
        return DomainItem(
            actor = item.actor,
            director = item.director,
            image = item.image,
            userRating = item.userRating,
            title = item.title,
            subtitle = item.subtitle,
            pubDate = item.pubDate,
            link = item.link
        )
    }

}
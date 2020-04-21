package io.github.sooakim.local.mapper

import io.github.sooakim.data.model.SAMovieData
import io.github.sooakim.local.model.SAMovieEntity

internal object SAMovieLocalMapper : SALocalMapper<SAMovieEntity, SAMovieData> {
    override fun mapToLocal(from: SAMovieData): SAMovieEntity {
        return SAMovieEntity(
            id = 0L,
            title = from.title,
            link = from.link,
            image = from.image,
            subtitle = from.subtitle,
            pubDate = from.pubDate,
            director = from.director,
            actor = from.actor,
            userRating = from.userRating
        )
    }

    override fun mapToData(from: SAMovieEntity): SAMovieData {
        return SAMovieData(
            title = from.title,
            link = from.link,
            image = from.image,
            subtitle = from.subtitle,
            pubDate = from.pubDate,
            director = from.director,
            actor = from.actor,
            userRating = from.userRating
        )
    }
}
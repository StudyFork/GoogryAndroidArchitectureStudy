package io.github.sooakim.data.local.mapper

import io.github.sooakim.data.model.SAMovieEntity
import io.github.sooakim.data.local.model.SAMovieEntity as SAMovieLocalModel

class SAMovieLocalMapper : SALocalMapper<SAMovieLocalModel, SAMovieEntity> {
    override fun mapToLocal(from: SAMovieEntity): SAMovieLocalModel {
        return SAMovieLocalModel(
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

    override fun mapToEntity(from: SAMovieLocalModel): SAMovieEntity {
        return SAMovieEntity(
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
package io.github.sooakim.data.mapper

import io.github.sooakim.data.model.SAMovieEntity
import io.github.sooakim.domain.model.SAMovieModel

class SAMovieMapper :
    SAMapper<SAMovieEntity, SAMovieModel> {
    override fun mapToModel(from: SAMovieEntity): SAMovieModel {
        return SAMovieModel(
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

    override fun mapToEntity(from: SAMovieModel): SAMovieEntity {
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
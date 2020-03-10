package io.github.sooakim.data.local.mapper

import io.github.sooakim.data.model.SAMovieData
import io.github.sooakim.data.local.model.SAMovieEntity as SAMovieLocalModel

object SAMovieLocalMapper : SALocalMapper<SAMovieLocalModel, SAMovieData> {
    override fun mapToLocal(from: SAMovieData): SAMovieLocalModel {
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

    override fun mapToData(from: SAMovieLocalModel): SAMovieData {
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
package io.github.sooakim.data.mapper

import io.github.sooakim.data.model.SAMovieData
import io.github.sooakim.domain.model.SAMovieModel

internal object SAMovieDataMapper :
    SADataMapper<SAMovieData, SAMovieModel> {
    override fun mapToModel(from: SAMovieData): SAMovieModel {
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
}
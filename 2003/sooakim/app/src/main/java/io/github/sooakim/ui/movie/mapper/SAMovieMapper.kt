package io.github.sooakim.ui.movie.mapper

import io.github.sooakim.domain.model.SAMovieModel
import io.github.sooakim.ui.mapper.SAPresentationMapper
import io.github.sooakim.ui.movie.model.SAMoviePresentation
import io.github.sooakim.util.ext.formatWith
import io.github.sooakim.util.ext.toDateWith
import java.util.Date

class SAMovieMapper : SAPresentationMapper<SAMovieModel, SAMoviePresentation> {
    override fun mapToPresentation(from: SAMovieModel): SAMoviePresentation {
        return SAMoviePresentation(
            title = from.title,
            link = from.link,
            image = from.image,
            subtitle = from.subtitle,
            pubDate = from.pubDate.formatWith(DATE_FORMAT_YEAR),
            director = from.director,
            actor = from.actor,
            userRating = from.userRating
        )
    }

    override fun mapToModel(from: SAMoviePresentation): SAMovieModel {
        return SAMovieModel(
            title = from.title,
            link = from.link,
            image = from.image,
            subtitle = from.subtitle,
            pubDate = from.pubDate.toDateWith(DATE_FORMAT_YEAR) ?: Date(0),
            director = from.director,
            actor = from.actor,
            userRating = from.userRating
        )
    }

    companion object {
        private const val DATE_FORMAT_YEAR = "yyyy"
    }
}
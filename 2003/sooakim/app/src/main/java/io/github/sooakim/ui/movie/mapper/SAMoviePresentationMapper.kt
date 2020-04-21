package io.github.sooakim.ui.movie.mapper

import io.github.sooakim.domain.model.SAMovieModel
import io.github.sooakim.remote.util.ext.formatWith
import io.github.sooakim.ui.mapper.SAPresentationMapper
import io.github.sooakim.ui.movie.model.SAMoviePresentation

object SAMoviePresentationMapper : SAPresentationMapper<SAMovieModel, SAMoviePresentation> {
    private const val DATE_FORMAT_YEAR = "yyyy"

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
}
package io.github.sooakim.data.remote.mapper

import io.github.sooakim.data.model.SAMovieData
import io.github.sooakim.util.ext.formatWith
import io.github.sooakim.util.ext.toDateWith
import io.github.sooakim.util.ext.toPlainFromHtml
import java.util.Date
import io.github.sooakim.data.remote.model.SAMovieModel as SAMovieRemoteModel

object SAMovieRemoteMapper : SARemoteMapper<SAMovieRemoteModel, SAMovieData> {
    private const val DATE_FORMAT_YEAR = "yyyy"

    override fun mapToRemote(from: SAMovieData): SAMovieRemoteModel {
        return SAMovieRemoteModel(
            title = from.title,
            link = from.link,
            image = from.image,
            subtitle = from.subtitle,
            pubDate = from.pubDate.formatWith(DATE_FORMAT_YEAR),
            director = from.director,
            actor = from.actor,
            userRating = from.userRating.toString()
        )
    }

    override fun mapToData(from: SAMovieRemoteModel): SAMovieData {
        return SAMovieData(
            title = from.title.toPlainFromHtml(),
            link = from.link,
            image = from.image,
            subtitle = from.subtitle.toPlainFromHtml(),
            pubDate = from.pubDate.toDateWith(DATE_FORMAT_YEAR) ?: Date(0),
            director = from.director.toPlainFromHtml(),
            actor = from.actor.toPlainFromHtml(),
            userRating = from.userRating.toFloatOrNull() ?: 0f
        )
    }
}
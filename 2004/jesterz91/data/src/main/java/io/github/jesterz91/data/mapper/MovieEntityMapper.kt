package io.github.jesterz91.data.mapper

import io.github.jesterz91.data.model.MovieEntity
import io.github.jesterz91.domain.model.Movie

object MovieEntityMapper : Mapper<MovieEntity, Movie> {

    override fun mapToDomain(entity: MovieEntity): Movie {
        return Movie(
            title = entity.title,
            subtitle = entity.subtitle,
            director = entity.director,
            actor = entity.actor,
            image = entity.image,
            link = entity.link,
            pubDate = entity.pubDate,
            userRating = entity.userRating
        )
    }
}
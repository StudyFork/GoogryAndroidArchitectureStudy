package io.github.jesterz91.local.mapper

import io.github.jesterz91.data.model.MovieEntity
import io.github.jesterz91.local.model.MovieLocal

object MovieLocalMapper : Mapper<MovieLocal, MovieEntity> {

    override fun mapToEntity(local: MovieLocal): MovieEntity {
        return MovieEntity(
            title = local.title,
            subtitle = local.subtitle,
            director = local.director,
            actor = local.actor,
            image = local.image,
            link = local.link,
            pubDate = local.pubDate,
            userRating = local.userRating
        )
    }

    override fun mapToLocal(entity: MovieEntity): MovieLocal {
        return MovieLocal(
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
package io.github.jesterz91.remote.mapper

import io.github.jesterz91.data.model.MovieEntity
import io.github.jesterz91.remote.model.MovieRemote

object MovieRemoteMapper : Mapper<MovieRemote, MovieEntity> {

    override fun mapToEntity(remote: MovieRemote): MovieEntity {
        return MovieEntity(
            title = remote.title,
            subtitle = remote.subtitle,
            director = remote.director,
            actor = remote.actor,
            image = remote.image,
            link = remote.link,
            pubDate = remote.pubDate,
            userRating = remote.userRating.toFloatOrNull() ?: 0f
        )
    }
}
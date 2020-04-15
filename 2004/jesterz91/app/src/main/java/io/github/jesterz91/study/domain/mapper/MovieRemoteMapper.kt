package io.github.jesterz91.study.domain.mapper

import io.github.jesterz91.study.data.remote.model.MovieRemote
import io.github.jesterz91.study.domain.entity.Movie

class MovieRemoteMapper : Mapper<List<Movie>, List<MovieRemote>> {

    override fun toData(domain: List<Movie>): List<MovieRemote> {
        return domain.map {
            MovieRemote(
                title = it.title,
                subtitle = it.subtitle,
                director = it.director,
                actor = it.actor,
                image = it.image,
                link = it.link,
                pubDate = it.pubDate,
                userRating = it.userRating.toString()
            )
        }
    }

    override fun toDomain(data: List<MovieRemote>): List<Movie> {
        return data.map {
            Movie(
                title = it.title,
                subtitle = it.subtitle,
                director = it.director,
                actor = it.actor,
                image = it.image,
                link = it.link,
                pubDate = it.pubDate,
                userRating = it.userRating.toFloatOrNull() ?: 0f
            )
        }
    }
}
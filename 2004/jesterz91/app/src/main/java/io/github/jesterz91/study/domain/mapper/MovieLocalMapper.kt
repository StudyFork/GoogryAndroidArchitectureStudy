package io.github.jesterz91.study.domain.mapper

import io.github.jesterz91.study.data.local.model.MovieLocal
import io.github.jesterz91.study.domain.entity.Movie

class MovieLocalMapper : Mapper<List<Movie>, List<MovieLocal>> {

    override fun toData(domain: List<Movie>): List<MovieLocal> {
        return domain.map {
            MovieLocal(
                title = it.title,
                subtitle = it.subtitle,
                director = it.director,
                actor = it.actor,
                image = it.image,
                link = it.link,
                pubDate = it.pubDate,
                userRating = it.userRating
            )
        }
    }

    override fun toDomain(data: List<MovieLocal>): List<Movie> {
        return data.map {
            Movie(
                title = it.title,
                subtitle = it.subtitle,
                director = it.director,
                actor = it.actor,
                image = it.image,
                link = it.link,
                pubDate = it.pubDate,
                userRating = it.userRating
            )
        }
    }
}
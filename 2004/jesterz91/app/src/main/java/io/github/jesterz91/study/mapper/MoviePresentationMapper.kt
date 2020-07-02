package io.github.jesterz91.study.mapper

import io.github.jesterz91.domain.model.Movie
import io.github.jesterz91.study.model.MoviePresentation

object MoviePresentationMapper : Mapper<Movie, MoviePresentation> {

    override fun mapToPresentation(domain: Movie): MoviePresentation {
        return MoviePresentation(
            title = domain.title,
            subtitle = domain.subtitle,
            director = domain.director,
            actor = domain.actor,
            image = domain.image,
            link = domain.link,
            pubDate = domain.pubDate,
            userRating = domain.userRating
        )
    }
}
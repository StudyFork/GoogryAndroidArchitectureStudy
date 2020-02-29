package com.jay.repository.mapper

import com.jay.local.model.MovieEntity
import com.jay.repository.model.Movie

object MovieEntityMapper : Mapper<Movie, MovieEntity> {
    override fun map(input: Movie): MovieEntity =
        MovieEntity(
            title = input.title,
            link = input.link,
            image = input.image,
            subtitle = input.subtitle,
            director = input.director,
            actor = input.actor,
            pubDate = input.pubDate,
            userRating = input.userRating
        )


    override fun reverseMap(output: MovieEntity): Movie =
        Movie(
            title = output.title,
            link = output.link,
            subtitle = output.subtitle,
            actor = output.actor,
            pubDate = output.pubDate,
            _userRating = (output.userRating * 2.toFloat()).toString(),
            image = output.image,
            director = output.director
        )
}
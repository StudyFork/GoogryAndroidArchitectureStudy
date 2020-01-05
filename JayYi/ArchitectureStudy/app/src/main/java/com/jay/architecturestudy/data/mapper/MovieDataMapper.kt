package com.jay.architecturestudy.data.mapper

import com.jay.architecturestudy.data.database.entity.MovieEntity
import com.jay.architecturestudy.data.model.Movie

object MovieDataMapper : Mapper<Movie, MovieEntity> {
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
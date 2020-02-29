package com.jay.repository.mapper

import com.jay.remote.model.MovieItem
import com.jay.repository.model.Movie

object MovieItemMapper : Mapper<Movie, MovieItem> {
    override fun map(input: Movie): MovieItem =
        MovieItem(
            title = input.title,
            link = input.link,
            image = input.image,
            subtitle = input.subtitle,
            director = input.director,
            actor = input.actor,
            pubDate = input.pubDate,
            _userRating = (input.userRating * 2.toFloat()).toString()
        )


    override fun reverseMap(output: MovieItem): Movie =
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
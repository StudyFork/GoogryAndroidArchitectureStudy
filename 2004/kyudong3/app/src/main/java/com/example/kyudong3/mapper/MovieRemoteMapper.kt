package com.example.kyudong3.mapper

import com.example.kyudong3.data.model.Movie
import com.example.kyudong3.data.remote.MovieRemote

class MovieRemoteMapper : Mapper<List<Movie>, List<MovieRemote>> {
    override fun toData(domain: List<Movie>): List<MovieRemote> {
        return domain.map {
            MovieRemote(
                title = it.title,
                link = it.link,
                image = it.image,
                subtitle = it.subtitle,
                pubDate = it.pubDate,
                director = it.director,
                actor = it.actor,
                userRating = it.userRating
            )
        }
    }

    override fun toDomain(data: List<MovieRemote>): List<Movie> {
        return data.map {
            Movie(
                title = it.title,
                link = it.link,
                image = it.image,
                subtitle = it.subtitle,
                pubDate = it.pubDate,
                director = it.director,
                actor = it.actor,
                userRating = it.userRating
            )
        }
    }
}
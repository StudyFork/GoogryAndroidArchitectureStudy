package com.kyudong.remote.mapper

import com.kyudong.data.mapper.Mapper
import com.kyudong.data.model.Movie
import com.kyudong.remote.network.MovieRemote

internal class MovieRemoteMapper :
    Mapper<List<Movie>, List<MovieRemote>> {
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
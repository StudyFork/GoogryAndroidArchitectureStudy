package com.kyudong.local.mapper

import com.kyudong.data.mapper.Mapper
import com.kyudong.data.model.Movie
import com.kyudong.local.db.MovieLocalEntity

internal class MovieLocalMapper :
    Mapper<List<Movie>, List<MovieLocalEntity>> {
    override fun toData(domain: List<Movie>): List<MovieLocalEntity> {
        return domain.map {
            MovieLocalEntity(
                title = it.title,
                link = it.link,
                image = it.image,
                subTitle = it.subtitle,
                pubDate = it.pubDate,
                director = it.director,
                actor = it.actor,
                userRating = it.userRating
            )
        }
    }

    override fun toDomain(data: List<MovieLocalEntity>): List<Movie> {
        return data.map {
            Movie(
                title = it.title,
                link = it.link,
                image = it.image,
                subtitle = it.subTitle,
                pubDate = it.pubDate,
                director = it.director,
                actor = it.actor,
                userRating = it.userRating
            )
        }
    }

}
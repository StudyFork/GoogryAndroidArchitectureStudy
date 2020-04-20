package com.example.kyudong3.mapper

import com.example.kyudong3.data.local.MovieLocalEntity
import com.example.kyudong3.data.model.Movie

class MovieLocalMapper : Mapper<List<Movie>, List<MovieLocalEntity>> {
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
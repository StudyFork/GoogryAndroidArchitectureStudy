package com.project.remote.response

import com.project.data.model.MovieItem
import com.project.data.model.NaverMovieEntity

data class NaverMovieResponse(
    val items: List<MovieItemResponse>
)

data class MovieItemResponse(
    val title: String = "",
    val link: String = "",
    val image: String = "",
    val subtitle: String = "",
    val pubDate: String = "",
    val director: String = "",
    val actor: String = "",
    val userRating: Double = 0.0
)

fun NaverMovieResponse.mapToNaverMovieEntity(): NaverMovieEntity =
    NaverMovieEntity(
        items = items.map {
            MovieItem(
                title = it.title,
                subtitle = it.subtitle,
                pubDate = it.pubDate,
                director = it.director,
                image = it.image,
                actor = it.actor,
                link = it.link,
                userRating = it.userRating
            )
        }
    )
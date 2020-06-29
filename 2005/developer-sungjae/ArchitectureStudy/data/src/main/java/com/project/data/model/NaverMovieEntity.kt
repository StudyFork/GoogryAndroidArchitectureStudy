package com.project.data.model

data class NaverMovieEntity(
    val items: List<MovieItem>
)

data class MovieItem(
    var title: String = "",
    var link: String = "",
    var image: String = "",
    var subtitle: String = "",
    var pubDate: String = "",
    var director: String = "",
    var actor: String = "",
    var userRating: Double = 0.0
)

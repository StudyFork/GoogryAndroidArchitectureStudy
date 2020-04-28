package com.sangjin.newproject.adapter

data class ResponseData(
    val items: List<Movie>
)

data class Movie(
    var title: String = "",
    var link: String = "",
    var image: String = "",
    var subtitle: String = "",
    var pubDate: String = "",
    var director: String = "",
    var actor: String = "",
    var userRating: String = ""
)

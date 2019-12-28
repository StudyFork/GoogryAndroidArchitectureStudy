package com.example.handnew04

data class NaverMovieResponse(
    var items: List<items>
)

data class items(
    val title: String,
    val link: String,
    val image: String,
    val pubDate: String,
    val director: String,
    val actor: String,
    val userRating: String
)


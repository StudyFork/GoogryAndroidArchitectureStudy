package com.example.kangraemin.response

data class ItemsMovieSearch(
    val items: ArrayList<ResponseMovieSearch>
)

data class ResponseMovieSearch(
    val title: String,
    val image: String,
    val director: String,
    val actor: String,
    val userRating: String,
    val pubDate: String
)
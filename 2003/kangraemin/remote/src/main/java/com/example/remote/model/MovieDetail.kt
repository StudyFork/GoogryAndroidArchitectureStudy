package com.example.remote.model

data class Movies(
    val items: ArrayList<MovieDetail>
)

data class MovieDetail(
    val title: String,
    val image: String,
    val director: String,
    val actor: String,
    val userRating: String,
    val pubDate: String
)
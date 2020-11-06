package com.hhi.myapplication.api

class MovieData {
    data class Response(
        val display: Int,
        val items: ArrayList<MovieItem>,
        val lastBuildDate: String,
        val start: Int,
        val total: Int
    )

    data class MovieItem(
        val actor: String,
        val director: String,
        val image: String,
        val link: String,
        val pubDate: String,
        val subtitle: String,
        val title: String,
        val userRating: String
    )
}
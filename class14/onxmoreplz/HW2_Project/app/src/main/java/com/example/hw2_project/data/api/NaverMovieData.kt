package com.example.hw2_project.data.api

class NaverMovieData {
    data class NaverMovieResponse(
        val display: Int,
        val items: List<NaverMovie>,
        val lastBuildDate: String,
        val start: Int,
        val total: Int,
    )

    data class NaverMovie(
        val actor: String,
        val director: String,
        val image: String,
        val link: String,
        val pubDate: String,
        val subtitle: String,
        val title: String,
        val userRating: String,
    )
}
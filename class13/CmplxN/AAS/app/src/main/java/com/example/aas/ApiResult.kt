package com.example.aas

import com.google.gson.annotations.SerializedName

data class ApiResult(
    val display: Int,
    @SerializedName("items")
    val movies: List<Movie>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
) {
	data class Movie(
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
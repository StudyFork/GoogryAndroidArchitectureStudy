package com.wybh.androidarchitecturestudy.model

data class ResponseCinemaData(
    val display: Int,
    val items: List<Item>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
) {
    data class Item(
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
package com.project.architecturestudy.models

class MovieData(
    val lastBuildDate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    val items: Array<Items>
) {
    data class Items(
        val title: String,
        val link: String,
        val image: String,
        val subtitle: String,
        val pubDate: String,
        val director: String,
        val actor: String,
        val userRating: Double
    )
}

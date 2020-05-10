package com.project.architecturestudy.data.model

class Movie(
    val lastBuildDate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    val items: ArrayList<Items>
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

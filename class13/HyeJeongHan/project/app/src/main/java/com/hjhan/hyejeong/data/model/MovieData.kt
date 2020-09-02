package com.hjhan.hyejeong.data.model

data class MovieData(
    val lastBuildDate: String,
    val total: Long,
    val start: Long,
    val display: Long,
    val items: List<Item>
)

data class Item(
    val title: String,
    val link: String,
    val image: String,
    val subtitle: String,
    val pubDate: String,
    val director: String,
    val actor: String,
    val userRating: String
)

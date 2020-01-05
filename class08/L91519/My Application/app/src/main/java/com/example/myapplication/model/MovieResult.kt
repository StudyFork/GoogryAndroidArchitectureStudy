package com.example.myapplication.model

data class MovieResult(
    val items: List<Item>
) {
    data class Item(
        val director: String?,
        val pubDate: String?,
        val subtitle: String?,
        val userRating: String?,
        val originallink: String?
    )
}
package com.ironelder.androidarchitecture.data


data class TotalModel(
    val display: Int,
    val items: List<Item>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)
data class Item(
    val actor: String,
    val director: String,
    val image: String,
    val link: String,
    val pubDate: String,
    val subtitle: String,
    val title: String,
    val userRating: String,
    val bloggerlink: String,
    val bloggername: String,
    val description: String,
    val postdate: String,
    val author: String,
    val discount: String,
    val isbn: String,
    val price: String,
    val pubdate: String,
    val publisher: String,
    val originallink: String
)
package com.ironelder.androidarchitecture.data

data class NewsModel(
    val display: Int,
    val items: List<NewsItem>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)
data class NewsItem(
    val description: String,
    val link: String,
    val originallink: String,
    val pubDate: String,
    val title: String
)
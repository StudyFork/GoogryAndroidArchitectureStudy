package com.tsdev.remote.source

//import com.tsdev.data.model.Item

data class MovieDomainResponse(
    val display: Int,
    val items: List<DomainItem>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)

data class DomainItem(
    val actor: String,
    val director: String,
    val image: String,
    val link: String,
    val pubDate: String,
    val subtitle: String,
    val title: String,
    val userRating: String
)
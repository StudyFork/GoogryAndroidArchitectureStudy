package com.hansung.firstproject

// 검색된 결과에 대한 여러 Movie들에 대한 data class
data class Movies(
    val lastBuildDate: String,
    val total: Long,
    val start: Int,
    val display: Int,
    val items: List<Movie>
)

// 검색된 결과 중 한 가지 movie에 대한 data class
data class Movie(
    val title: String,
    val link: String,
    val image: String,
    val subtitle: String,
    val pubDate: String,
    val director: String,
    val actor: String,
    val userRating: String
)
package com.hansung.firstproject.data

// 검색된 결과 중 한 movie에 대한 data class
data class MovieModel(
    val title: String,
    val link: String,
    val image: String,
    val pubDate: String,
    val director: String,
    val actor: String,
    val userRating: String
)
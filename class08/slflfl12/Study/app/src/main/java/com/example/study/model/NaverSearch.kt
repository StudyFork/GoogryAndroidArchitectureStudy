package com.example.study.model

data class NaverSearch(
    val display: Int,
    val items: List<Movie>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)
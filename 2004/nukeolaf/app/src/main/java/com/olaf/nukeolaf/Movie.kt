package com.olaf.nukeolaf

data class Movie(
    val lastBuildDate: String,
    val total: String,
    val start: String,
    val display: String,
    val items: List<Item>
)
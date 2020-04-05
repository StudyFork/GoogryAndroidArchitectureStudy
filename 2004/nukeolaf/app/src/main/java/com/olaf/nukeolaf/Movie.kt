package com.olaf.nukeolaf

data class Movie(
    val title: String,
    val link: String,
    val description: String,
    val lastBuildDate: String,
    val total: String,
    val start: String,
    val display: String,
    val items: List<Item>
)
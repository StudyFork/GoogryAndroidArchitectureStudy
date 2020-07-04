package com.tsdev.data.model

import com.tsdev.domain.model.Item

data class MovieResponse(
    val display: Int,
    val items: List<Item>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)
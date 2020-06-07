package com.tsdev.remote.source

import com.tsdev.data.source.Item

data class MovieDomainResponse(
    val display: Int,
    val items: List<Item>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)
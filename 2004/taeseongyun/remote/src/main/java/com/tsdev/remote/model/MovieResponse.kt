package com.tsdev.remote.model

data class MovieResponse(
    val total: Int,
    val start: Int,
    val items: List<RemoteItem>,
    val display: Int
)
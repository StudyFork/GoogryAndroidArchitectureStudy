package com.byiryu.data.model

data class Response(
    val total: Int,
    val start: Int,
    val display: Int,
    val lastBuildDate: String,
    val items: List<Item>
)
package com.hyper.hyapplication

data class ResultGetSearchMovie(
    var lastBuildDate: String,
    var total: Int,
    var start: Int,
    var display: Int,
    var items: List<Item>
) {
    data class Item(
        val title: String,
        val image: String,
        val director: String,
        val actor: String
    )
}
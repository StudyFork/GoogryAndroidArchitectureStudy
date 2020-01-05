package com.god.taeiim.myapplication.api.model

data class SearchResultShow(
    val items: List<Item>
) {
    data class Item(
        var title: String?,
        var subtitle: String?,
        var description: String?,
        var link: String?,
        var image: String?
    )
}


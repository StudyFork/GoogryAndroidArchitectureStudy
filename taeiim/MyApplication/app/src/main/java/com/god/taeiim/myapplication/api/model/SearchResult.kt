package com.god.taeiim.myapplication.api.model

data class SearchResult(
        var items: List<Item>
) {
    data class Item(
            val items: ArrayList<Item>,
            val bloggerlink: String?,
            val bloggername: String?,
            val description: String?,
            val link: String?,
            val postdate: String?,
            val title: String?,
            val author: String?,
            val discount: String?,
            val image: String?,
            val isbn: String?,
            val price: String?,
            val pubdate: String?,
            val publisher: String?,
            val actor: String?,
            val director: String?,
            val pubDate: String?,
            val subtitle: String?,
            val userRating: String?,
            val originallink: String?
    )
}


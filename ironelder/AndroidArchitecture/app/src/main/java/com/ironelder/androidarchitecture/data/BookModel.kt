package com.ironelder.androidarchitecture.data

data class BookModel(
    val display: Int,
    val items: List<BookItem>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)
data class BookItem(
    val author: String,
    val description: String,
    val discount: String,
    val image: String,
    val isbn: String,
    val link: String,
    val price: String,
    val pubdate: String,
    val publisher: String,
    val title: String
)

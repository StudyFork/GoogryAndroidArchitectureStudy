package com.ironelder.androidarchitecture.data

data class BlogModel(
    val display: Int,
    val items: List<BlogItem>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)

data class BlogItem(
    val bloggerlink: String,
    val bloggername: String,
    val description: String,
    val link: String,
    val postdate: String,
    val title: String
)
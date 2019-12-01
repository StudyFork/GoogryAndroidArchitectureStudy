package com.jay.architecturestudy.model

import com.google.gson.annotations.SerializedName

data class Blog(
    @SerializedName("bloggerlink")
    val bloggerLink: String,
    @SerializedName("bloggername")
    val bloggerName: String,
    val description: String,
    val link: String,
    val postdate: String,
    val title: String
)

data class ResponseBlog(
    val display: Int,
    @SerializedName("items")
    val blogs: List<Blog>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)


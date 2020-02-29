package com.jay.remote.model

import com.google.gson.annotations.SerializedName

data class BlogItem(
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
    val blogs: List<BlogItem>
)
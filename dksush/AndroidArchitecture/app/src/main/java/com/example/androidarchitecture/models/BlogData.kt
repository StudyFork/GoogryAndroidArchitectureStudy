package com.example.androidarchitecture.models

import com.google.gson.annotations.SerializedName

data class BlogData (
    @SerializedName("items")
    val blogs: List<ResponseBlog>

)

data class ResponseBlog(
    @SerializedName("bloggerlink")
    val bloggerLink: String,
    @SerializedName("bloggername")
    val bloggerName: String,
    val description: String,
    val link: String,
    val postdate: String,
    val title: String
)

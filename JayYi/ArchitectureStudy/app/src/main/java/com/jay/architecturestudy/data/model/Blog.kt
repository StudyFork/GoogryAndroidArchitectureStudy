package com.jay.architecturestudy.data.model

import com.google.gson.annotations.SerializedName
import com.jay.architecturestudy.data.database.entity.BlogEntity

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

data class BlogRepo(
    val keyword: String,
    val blogs: List<Blog>
)

data class ResponseBlog(
    val blogs: List<Blog>
)

data class BlogLocalData(
    val blogs: List<BlogEntity>
)



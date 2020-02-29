package com.jay.repository.model

import com.jay.remote.model.BlogItem

data class Blog(
    val bloggerLink: String,
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

fun Blog.map(blogItem: BlogItem) =
    Blog(
        bloggerLink = blogItem.bloggerLink,
        bloggerName = blogItem.bloggerName,
        description = blogItem.description,
        link = blogItem.link,
        postdate = blogItem.postdate,
        title = blogItem.title
    )


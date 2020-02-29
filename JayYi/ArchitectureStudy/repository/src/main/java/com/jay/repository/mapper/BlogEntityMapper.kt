package com.jay.repository.mapper

import com.jay.local.model.BlogEntity
import com.jay.repository.model.Blog

object BlogEntityMapper : Mapper<Blog, BlogEntity> {
    override fun map(input: Blog): BlogEntity =
        BlogEntity(
            bloggerLink = input.bloggerLink,
            bloggerName = input.bloggerName,
            description = input.description,
            link = input.link,
            postdate = input.postdate,
            title = input.title
        )

    override fun reverseMap(output: BlogEntity): Blog =
        Blog(
            bloggerLink = output.bloggerLink,
            bloggerName = output.bloggerName,
            description = output.description,
            link = output.link,
            postdate = output.postdate,
            title = output.title
        )

}
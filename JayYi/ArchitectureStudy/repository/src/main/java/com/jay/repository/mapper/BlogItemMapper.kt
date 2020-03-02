package com.jay.repository.mapper

import com.jay.remote.model.BlogItem
import com.jay.repository.model.Blog

object BlogItemMapper : Mapper<Blog, BlogItem> {
    override fun map(input: Blog): BlogItem =
        BlogItem(
            bloggerLink = input.bloggerLink,
            bloggerName = input.bloggerName,
            description = input.description,
            link = input.link,
            postdate = input.postdate,
            title = input.title
        )

    override fun reverseMap(output: BlogItem): Blog =
        Blog(
            bloggerLink = output.bloggerLink,
            bloggerName = output.bloggerName,
            description = output.description,
            link = output.link,
            postdate = output.postdate,
            title = output.title
        )
}

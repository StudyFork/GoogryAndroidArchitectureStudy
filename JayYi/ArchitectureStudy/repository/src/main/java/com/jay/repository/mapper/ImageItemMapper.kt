package com.jay.repository.mapper

import com.jay.remote.model.ImageItem
import com.jay.repository.model.Image

object ImageItemMapper : Mapper<Image, ImageItem> {
    override fun map(input: Image): ImageItem =
        ImageItem(
            link = input.link,
            sizeWidth = input.sizeWidth,
            sizeHeight = input.sizeHeight,
            thumbnail = input.thumbnail,
            title = input.title
        )

    override fun reverseMap(output: ImageItem): Image =
        Image(
            link = output.link,
            sizeHeight = output.sizeHeight,
            sizeWidth = output.sizeWidth,
            thumbnail = output.thumbnail,
            title = output.title
        )
}
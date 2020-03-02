package com.jay.repository.mapper

import com.jay.local.model.ImageEntity
import com.jay.repository.model.Image

object ImageEntityMapper : Mapper<Image, ImageEntity> {
    override fun map(input: Image): ImageEntity =
        ImageEntity(
            link = input.link,
            sizeWidth = input.sizeWidth,
            sizeHeight = input.sizeHeight,
            thumbnail = input.thumbnail,
            title = input.title
        )

    override fun reverseMap(output: ImageEntity): Image =
        Image(
            link = output.link,
            sizeHeight = output.sizeHeight,
            sizeWidth = output.sizeWidth,
            thumbnail = output.thumbnail,
            title = output.title
        )
}
package com.jay.architecturestudy.data.mapper

import com.jay.architecturestudy.data.database.entity.ImageEntity
import com.jay.architecturestudy.data.model.Image

object ImageDataMapper : Mapper<Image, ImageEntity> {
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




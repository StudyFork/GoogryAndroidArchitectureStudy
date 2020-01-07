package com.jay.architecturestudy.data.model

import com.google.gson.annotations.SerializedName
import com.jay.architecturestudy.data.database.entity.ImageEntity

data class Image(
    val link: String,
    @SerializedName("sizeheight")
    val sizeHeight: String,
    @SerializedName("sizewidth")
    val sizeWidth: String,
    val thumbnail: String,
    val title: String
)

data class ImageRepo(
    val keyword: String,
    val images: List<Image>
)

data class ResponseImage(
    val images: List<Image>
)

data class ImageLocalData(
    val images: List<ImageEntity>
)


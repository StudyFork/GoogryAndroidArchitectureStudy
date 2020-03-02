package com.jay.remote.model

import com.google.gson.annotations.SerializedName

data class ImageItem(
    val link: String,
    @SerializedName("sizeheight")
    val sizeHeight: String,
    @SerializedName("sizewidth")
    val sizeWidth: String,
    val thumbnail: String,
    val title: String
)

data class ResponseImage(
    val images: List<ImageItem>
)

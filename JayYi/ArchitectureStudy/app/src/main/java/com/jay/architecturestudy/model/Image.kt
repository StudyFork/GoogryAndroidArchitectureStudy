package com.jay.architecturestudy.model

import com.google.gson.annotations.SerializedName

data class Image(
    val link: String,
    @SerializedName("sizeheight")
    val sizeHeight: String,
    @SerializedName("sizewidth")
    val sizeWidth: String,
    val thumbnail: String,
    val title: String
)

data class ResponseImages(
    val display: Int,
    @SerializedName("items")
    val images: List<Image>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)


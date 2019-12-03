package com.example.androidarchitecture.models

import com.google.gson.annotations.SerializedName

data class ImageData(

    @SerializedName("items")
    val images: List<ResponseImage>
)

data class ResponseImage(
    @SerializedName("title")
    val title: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("thumbnail")
    val thumbnail: String
)

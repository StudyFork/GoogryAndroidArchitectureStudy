package com.example.androidarchitecture.models

import com.google.gson.annotations.SerializedName

data class ImageData(
    @SerializedName("title")
    val title: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("thumbnail")
    val thumbnail: String
)

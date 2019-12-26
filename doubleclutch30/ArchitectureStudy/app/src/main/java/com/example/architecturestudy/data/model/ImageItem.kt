package com.example.architecturestudy.data.model

import com.google.gson.annotations.SerializedName

data class ImageItem(

    @SerializedName("thumbnail")
    val thumbnail : String,
    @SerializedName("sizeheight")
    val sizeheight : String,
    @SerializedName("sizewidth")
    val sizewidth : String,
    @SerializedName("link")
    val link : String
)
package com.example.architecturestudy.model

import com.google.gson.annotations.SerializedName

data class ImageData (val items : List<ImageItems>)

data class ImageItems(

    @SerializedName("thumbnail")
    val thumbnail : String,
    @SerializedName("sizeheight")
    val sizeheight : String,
    @SerializedName("sizewidth")
    val sizewidth : String,
    @SerializedName("link")
    val link : String

)
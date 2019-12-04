package com.example.studyapplication.vo

import com.google.gson.annotations.SerializedName

class ImageList {
    @SerializedName("items")
    val arrImageInfo = emptyArray<ImageInfo>()

    data class ImageInfo(
        var thumbnail : String,
        var link : String,
        var title : String
    )
}
package com.example.architecturestudy.data.model

import com.example.architecturestudy.data.local.Entity.BlogEntity
import com.example.architecturestudy.data.local.Entity.KinEntity
import com.google.gson.annotations.SerializedName

data class KinItem(

    @SerializedName("title")
    val title : String,
    @SerializedName("description")
    val description : String,
    @SerializedName("link")
    val link : String
) {
    fun toEntity() = KinEntity(
        title = title,
        description = description,
        link = link
    )
}
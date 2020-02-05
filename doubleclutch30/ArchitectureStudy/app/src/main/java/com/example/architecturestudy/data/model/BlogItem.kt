package com.example.architecturestudy.data.model

import com.example.architecturestudy.data.local.Entity.BlogEntity
import com.google.gson.annotations.SerializedName

data class BlogItem(

    @SerializedName("title")
    val title : String,
    @SerializedName("description")
    val description : String,
    @SerializedName("bloggername")
    val bloggername : String,
    @SerializedName("link")
    val link : String ,
    @SerializedName("postdate")
    val postdate : String
) {
    fun toEntity() = BlogEntity(
        title = title,
        description = description,
        bloggername = bloggername,
        link = link,
        postdate = postdate
    )
}

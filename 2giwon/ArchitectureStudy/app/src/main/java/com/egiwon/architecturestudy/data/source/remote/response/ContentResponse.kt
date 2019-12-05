package com.egiwon.architecturestudy.data.source.remote.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

data class ContentResponse(
    val query: String,
    @SerializedName("items")
    val contentItems: List<ContentItem>
)

@Keep
data class ContentItem(

    @SerializedName("image")
    val image: String = "",
    @SerializedName("subtitle")
    val subtitle: String = "",
    @SerializedName("pubdate")
    val pubDate: String = "",
    @SerializedName("director")
    val director: String = "",
    @SerializedName("actor")
    val actor: String = "",
    @SerializedName("userRating")
    val userRating: Double = 0.00,

    @SerializedName("bloggerlink")
    val bloggerlink: String = "",
    @SerializedName("bloggername")
    val bloggername: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("link")
    val link: String = "",
    @SerializedName("postdate")
    val postdate: String = "",
    @SerializedName("title")
    val title: String = "",

    @SerializedName("originallink")
    val originallink: String = ""
)
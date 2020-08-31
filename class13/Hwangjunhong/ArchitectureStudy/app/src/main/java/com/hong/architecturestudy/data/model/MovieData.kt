package com.hong.architecturestudy.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieData(
    @SerializedName("actor")
    @Expose
    val actor: String,

    @SerializedName("director")
    @Expose
    val director: String,

    @SerializedName("image")
    @Expose
    val image: String,

    @SerializedName("link")
    @Expose
    val link: String,

    @SerializedName("pubDate")
    @Expose
    val pubDate: String,

    @SerializedName("subtitle")
    @Expose
    val subtitle: String,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("userRating")
    @Expose
    val userRating: String
)
package com.lllccww.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieItem(
/*    val title: String,
    val link: String,
    val image: String,
    val subtitle: String,
    val pubDate: String,
    val director: String,
    val userRating: String*/


    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("link")
    @Expose
    val link: String,

    @SerializedName("image")
    @Expose
    val image: String,

    @SerializedName("subtitle")
    @Expose
    val subtitle: String,

    @SerializedName("pubDate")
    @Expose
    val pubDate: String,

    @SerializedName("director")
    @Expose
    val director: String,

    @SerializedName("userRating")
    @Expose
    val userRating: String

)
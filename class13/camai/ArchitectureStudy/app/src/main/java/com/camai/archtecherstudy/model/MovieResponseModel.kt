package com.camai.archtecherstudy.model

import com.google.gson.annotations.SerializedName

data class MovieResponseModel(
    @SerializedName("lastBuildDate")
    var lastBuildDate: String,
    @SerializedName("total")
    var total: Int,
    @SerializedName("start")
    var start: Int,
    @SerializedName("display")
    var display: Int,
    @SerializedName("items")
    var items: ArrayList<Items>
)

data class Items(
    @SerializedName("title")
    val title: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("pubDate")
    val pubDate: String,
    @SerializedName("director")
    val director: String,
    @SerializedName("actor")
    val actor: String,
    @SerializedName("userRating")
    val userRating: Float
)


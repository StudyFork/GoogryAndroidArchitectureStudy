package com.project.architecturestudy.models

import com.google.gson.annotations.SerializedName

class MovieData(
    @SerializedName("lastBuildDate") val lastBuildDate: String,
    @SerializedName("total") val total: Int,
    @SerializedName("start") val start: Int,
    @SerializedName("display") val display: Int,
    @SerializedName("items") val items: Array<Items>
) {
    data class Items(
        @SerializedName("title")
        val title: String,
        @SerializedName("link")
        val link: String,
        @SerializedName("image")
        val image: String,
        @SerializedName("subtitle")
        val subtitle: String,
        @SerializedName("pubdate")
        val pubDate: String,
        @SerializedName("director")
        val director: String,
        @SerializedName("actor")
        val actor: String,
        @SerializedName("userRating")
        val userRating: Double
    )
}

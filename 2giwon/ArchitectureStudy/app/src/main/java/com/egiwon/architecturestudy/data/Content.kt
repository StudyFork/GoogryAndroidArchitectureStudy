package com.egiwon.architecturestudy.data

import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("lastBuildDate") var lastBuildDate: String,
    @SerializedName("total") var total: Int,
    @SerializedName("start") var start: Int,
    @SerializedName("display") var display: Int,
    @SerializedName("items") var items: List<Item>
) {
    data class Item(

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
}
package com.egiwon.architecturestudy.data

import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("lastBuildDate")
    val lastBuildDate: String,
    @SerializedName("total")
    val total: Int,
    @SerializedName("start")
    val start: Int,
    @SerializedName("display")
    val display: Int,
    @SerializedName("items")
    val items: List<Item>
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
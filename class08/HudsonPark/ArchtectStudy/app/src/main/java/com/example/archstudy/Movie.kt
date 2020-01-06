package com.example.archstudy

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.util.*

data class Movie(
    @SerializedName("lastBuildDate") private val lastBuildDate: LocalDateTime,
    @SerializedName("items") private val items: Items,
    @SerializedName("title") private val title: String,
    @SerializedName("link") private val link: String,
    @SerializedName("image") private val image: String,
    @SerializedName("subtitle") private val subtitle: String,
    @SerializedName("pubDate") private val pubDate: Date,
    @SerializedName("director") private val director: String,
    @SerializedName("actor") private val actor: String,
    @SerializedName("userRating") private val userRating: Int
)
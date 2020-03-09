package com.example.myapplication.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * api응답 받는 값
 * */
data class RSP_SearchMovieInfo (
    @SerializedName("lastBuildDate")
    @Expose
    val lastBuildDate: String,
    @SerializedName("total")
    @Expose
    val total: Int,
    @SerializedName("start")
    @Expose
    val start: Int,
    @SerializedName("display")
    @Expose
    val display: Int,
    @SerializedName("items")
    @Expose
    val items: List<Item_SearchMovie>
)
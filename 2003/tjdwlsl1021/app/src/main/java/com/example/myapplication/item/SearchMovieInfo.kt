package com.example.myapplication.item

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SearchMovieInfo {
    @SerializedName("lastBuildDate")
    @Expose
    var lastBuildDate: String? = null
    @SerializedName("total")
    @Expose
    var total: Int? = null
    @SerializedName("start")
    @Expose
    var start: Int? = null
    @SerializedName("display")
    @Expose
    var display: Int? = null
    @SerializedName("items")
    @Expose
    var items: List<SearchMovieItem>? = null

}
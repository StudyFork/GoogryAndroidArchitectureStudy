package com.example.myapplication.item

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SearchMovieItem {
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("link")
    @Expose
    var link: String? = null
    @SerializedName("image")
    @Expose
    var image: String? = null
    @SerializedName("subtitle")
    @Expose
    var subtitle: String? = null
    @SerializedName("pubDate")
    @Expose
    var pubDate: String? = null
    @SerializedName("director")
    @Expose
    var director: String? = null
    @SerializedName("actor")
    @Expose
    var actor: String? = null
    @SerializedName("userRating")
    @Expose
    var userRating: String? = null

}
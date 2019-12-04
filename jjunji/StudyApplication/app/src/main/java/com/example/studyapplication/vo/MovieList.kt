package com.example.studyapplication.vo

import com.google.gson.annotations.SerializedName

class MovieList {
    @SerializedName("items")
    val arrMovieInfo = emptyArray<MovieInfo>()

    data class MovieInfo (
        var title: String,
        var image: String,
        var pubDate: String,
        var director: String,
        var actor: String )
}
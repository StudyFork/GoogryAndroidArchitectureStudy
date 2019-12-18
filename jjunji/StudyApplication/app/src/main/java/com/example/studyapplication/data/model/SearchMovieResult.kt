package com.example.studyapplication.data.model

import com.google.gson.annotations.SerializedName

class SearchMovieResult {
    @SerializedName("items")
    val arrMovieInfo = emptyArray<MovieInfo>()

    data class MovieInfo (
        var title: String,
        var image: String,
        var pubDate: String,
        var director: String,
        var actor: String )
}
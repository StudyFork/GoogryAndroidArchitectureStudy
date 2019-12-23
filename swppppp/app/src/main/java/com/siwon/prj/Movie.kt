package com.siwon.prj

import com.google.gson.annotations.SerializedName

data class Movies(@SerializedName("items") val movies: ArrayList<Movie>)

data class Movie(
    val title: String,
    val link: String,
    val image: String,
    val subtitle: String,
    val pubDate: String,
    val director: String,
    val actor: String,
    val userRating: String
)

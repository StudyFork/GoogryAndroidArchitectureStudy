package com.mtjin.data.model.search
import com.google.gson.annotations.SerializedName
import com.mtjin.data.model.search.Movie

data class MovieResponse(
    val display: Int,
    val movies: List<Movie>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)
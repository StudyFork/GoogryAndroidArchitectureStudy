package com.example.architecturestudy.data.model

import com.example.architecturestudy.data.local.entity.MovieEntity
import com.google.gson.annotations.SerializedName

data class MovieItem(

    @SerializedName("title")
    val title: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("year")
    val year: String?,
    @SerializedName("director")
    val director: String,
    @SerializedName("actor")
    val actor: String,
    @SerializedName("userRating")
    val rating: Float?
) {
    fun toEntity() = MovieEntity(
        title = title,
        link = link,
        image = image,
        year = year ?: "",
        director = director,
        actor = actor,
        rating = rating
    )
}
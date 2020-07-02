package io.github.jesterz91.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class MovieRemote(
    @Json(name = "title")
    val title: String,
    @Json(name = "subtitle")
    val subtitle: String,
    @Json(name = "director")
    val director: String,
    @Json(name = "actor")
    val actor: String,
    @Json(name = "image")
    val image: String,
    @Json(name = "link")
    val link: String,
    @Json(name = "pubDate")
    val pubDate: String,
    @Json(name = "userRating")
    val userRating: String
)
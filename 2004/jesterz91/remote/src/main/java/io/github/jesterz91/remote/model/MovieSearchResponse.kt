package io.github.jesterz91.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieSearchResponse(
    @Json(name = "lastBuildDate")
    val lastBuildDate: String,
    @Json(name = "total")
    val total: Int,
    @Json(name = "start")
    val start: Int,
    @Json(name = "display")
    val display: Int,
    @Json(name = "items")
    val items: List<MovieRemote>
)
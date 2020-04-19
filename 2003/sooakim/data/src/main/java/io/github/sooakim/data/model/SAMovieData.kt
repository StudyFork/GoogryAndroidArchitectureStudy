package io.github.sooakim.data.model

import java.util.Date

data class SAMovieData(
    val title: String,

    val link: String,

    val image: String,

    val subtitle: String,

    val pubDate: Date,

    val director: String,

    val actor: String,

    val userRating: Float
) : SAData
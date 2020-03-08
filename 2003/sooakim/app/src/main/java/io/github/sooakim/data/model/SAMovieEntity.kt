package io.github.sooakim.data.model

import java.util.*

data class SAMovieEntity(
    val title: String,

    val link: String,

    val image: String,

    val subtitle: String,

    val pubDate: Date,

    val director: String,

    val actor: String,

    val userRating: Float
) : SAEntity
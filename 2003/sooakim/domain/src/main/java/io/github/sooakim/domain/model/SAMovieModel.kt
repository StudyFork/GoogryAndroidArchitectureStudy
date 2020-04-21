package io.github.sooakim.domain.model

import java.util.Date

data class SAMovieModel(
    val title: String,

    val link: String,

    val image: String,

    val subtitle: String,

    val pubDate: Date,

    val director: String,

    val actor: String,

    val userRating: Float
) : io.github.sooakim.domain.model.SAModel
package io.github.sooakim.ui.movie.model

import io.github.sooakim.ui.base.SAIdentifiable
import io.github.sooakim.ui.model.SAViewModel

data class SAMovieViewModel(
    val title: String,

    val link: String,

    val image: String,

    val subtitle: String,

    val pubDate: String,

    val director: String,

    val actor: String,

    val userRating: Float
) : SAViewModel, SAIdentifiable {
    override val identifier: Any
        get() = subtitle
}
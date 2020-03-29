package io.github.sooakim.ui.movie.model

import io.github.sooakim.ui.base.SAClickable
import io.github.sooakim.ui.base.SAIdentifiable
import io.github.sooakim.ui.model.SAPresentation
import io.reactivex.subjects.Subject

data class SAMoviePresentation(
    val title: String,

    val link: String,

    val image: String,

    val subtitle: String,

    val pubDate: String,

    val director: String,

    val actor: String,

    val userRating: Float
) : SAPresentation, SAIdentifiable, SAClickable<SAMoviePresentation> {
    override val identifier: Any
        get() = subtitle

    override lateinit var onClick: Subject<SAMoviePresentation>
}
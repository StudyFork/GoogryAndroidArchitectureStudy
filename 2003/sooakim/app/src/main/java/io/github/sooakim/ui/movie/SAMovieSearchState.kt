package io.github.sooakim.ui.movie

import io.github.sooakim.ui.base.SAState

sealed class SAMovieSearchState : SAState {
    data class ShowMovieLink(
        val movieLink: String
    ) : SAMovieSearchState()
}
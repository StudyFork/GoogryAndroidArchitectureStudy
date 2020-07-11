package com.example.architecturestudy.data.repository

import com.example.architecturestudy.data.model.MovieData
import com.example.architecturestudy.presenter.SearchMovieConstract

interface MovieRepository: SearchMovieConstract.Presenter {
    override fun remoteSearchMovie(
        movieTitle: String,
        success: (List<MovieData>) -> Unit,
        fail: (Throwable) -> Unit
    )
}
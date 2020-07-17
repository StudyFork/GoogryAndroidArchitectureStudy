package com.example.architecturestudy.data.repository

import com.example.architecturestudy.data.model.MovieData

interface MovieRepository {
    fun searchMovieOnRemote(
        movieTitle: String,
        success: (List<MovieData>) -> Unit,
        fail: (Throwable) -> Unit
    )
}
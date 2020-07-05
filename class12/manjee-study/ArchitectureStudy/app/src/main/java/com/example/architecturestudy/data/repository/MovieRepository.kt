package com.example.architecturestudy.data.repository

import com.example.architecturestudy.data.model.MovieData

interface MovieRepository {
    fun remoteSearchMovie(
        title: String,
        success: (List<MovieData>) -> Unit,
        fail: (Throwable) -> Unit
    )
}
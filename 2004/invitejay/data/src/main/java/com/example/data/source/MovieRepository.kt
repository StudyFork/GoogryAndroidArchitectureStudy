package com.example.data.source

import com.example.data.model.Movie

interface MovieRepository {

    fun getRemoteMovieList(
        query: String,
        onSuccess: (List<Movie>) -> Unit,
        onFailure: (Throwable) -> Unit
    )
}
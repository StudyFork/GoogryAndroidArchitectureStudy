package com.example.data.source.remote

import com.example.data.model.Movie

interface MovieRemoteDataSource {

    fun getMovieList(
        query: String,
        onSuccess: (List<Movie>) -> Unit,
        onFailure: (Throwable) -> Unit
    )
}
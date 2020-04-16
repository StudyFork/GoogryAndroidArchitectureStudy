package com.example.studyforkandroid.data.source.remote

import com.example.studyforkandroid.data.Movie

interface MovieRemoteDataSource {

    fun getMovieList(
        query: String,
        onSuccess: (List<Movie>) -> Unit,
        onFailure: (Throwable) -> Unit
    )
}
package com.example.studyforkandroid.data.source

import com.example.studyforkandroid.data.Movie

interface MovieRepository {
    fun getMovieList(
        query: String,
        onSuccess: (List<Movie>) -> Unit,
        onFailure: (Throwable) -> Unit
    )
}
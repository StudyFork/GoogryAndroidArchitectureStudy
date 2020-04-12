package com.example.kyudong3.data.remote

import com.example.kyudong3.data.model.Movie

interface MovieDataSource {
    fun getMovieList(query: String, success: (List<Movie>) -> Unit, failure: (Throwable) -> Unit)
}
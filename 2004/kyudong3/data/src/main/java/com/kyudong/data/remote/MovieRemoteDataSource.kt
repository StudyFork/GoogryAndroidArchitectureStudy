package com.kyudong.data.remote

import com.kyudong.data.model.Movie

interface MovieRemoteDataSource {
    fun getMovieList(query: String, success: (List<Movie>) -> Unit, failure: (Throwable) -> Unit)
}
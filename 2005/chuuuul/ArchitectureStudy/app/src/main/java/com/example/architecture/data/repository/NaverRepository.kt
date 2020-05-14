package com.example.architecture.data.repository

import com.example.architecture.data.model.MovieModel

interface NaverRepository {

    fun getMovieList(
        keyword: String,
        onSuccess: (movieList: List<MovieModel>) -> Unit,
        onFailure: (t: Throwable) -> Unit
    )

    fun clearCacheData()
}
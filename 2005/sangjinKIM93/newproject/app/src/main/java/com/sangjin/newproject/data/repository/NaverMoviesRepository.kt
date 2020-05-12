package com.sangjin.newproject.data.repository

import com.sangjin.newproject.adapter.Movie

interface NaverMoviesRepository {

    fun getNaverMovies(
        query: String,
        onSuccess: ((movies: List<Movie>) -> Unit),
        onFailure: ((t:Throwable) -> Unit)
    )

}
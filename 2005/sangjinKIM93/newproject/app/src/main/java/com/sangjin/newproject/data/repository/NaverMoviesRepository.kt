package com.sangjin.newproject.data.repository

import com.sangjin.newproject.data.model.Movie

interface NaverMoviesRepository {

    fun getNaverMovies(
        query: String,
        onSuccess: ((movies: List<Movie>) -> Unit),
        onFailure: ((t: Throwable) -> Unit)
    )

}
package com.project.architecturestudy.data.repository

import com.project.architecturestudy.data.model.Movie

interface NaverMovieRepository {

    fun getMovieList(
        query: String,
        Success: ((Movie.Items) -> Unit),
        Failure: ((Throwable) -> Unit)
    )
}
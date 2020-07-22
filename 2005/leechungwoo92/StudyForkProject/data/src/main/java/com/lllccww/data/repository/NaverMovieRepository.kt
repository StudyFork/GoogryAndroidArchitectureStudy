package com.lllccww.data.repository

import com.lllccww.data.model.MovieItem

interface NaverMovieRepository {

    fun getSearchMovie(
        keyWord: String,
        success: (List<MovieItem>) -> Unit,
        failure: (Throwable) -> Unit
    )
}
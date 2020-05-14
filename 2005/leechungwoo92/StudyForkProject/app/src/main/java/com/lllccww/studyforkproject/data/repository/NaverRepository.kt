package com.lllccww.studyforkproject.data.repository

import com.lllccww.studyforkproject.data.model.MovieItem

interface NaverMovieRepository {

    fun getSearchMovie(
        keyWord: String,
        success: (List<MovieItem>) -> Unit,
        failure: (Throwable) -> Unit
    )
}
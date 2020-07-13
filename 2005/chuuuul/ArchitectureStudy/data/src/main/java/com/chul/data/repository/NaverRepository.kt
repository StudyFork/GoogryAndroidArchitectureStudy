package com.chul.data.repository

import com.chul.data.model.MovieModel

interface NaverRepository {

    fun getMovieList(
        keyword: String,
        onSuccess: (movieList: List<MovieModel>) -> Unit,
        onFailure: (t: Throwable) -> Unit
    )

    fun clearCacheData()

    fun saveMovieList(
        keyword: String,
        movieList: List<MovieModel>
    )


}
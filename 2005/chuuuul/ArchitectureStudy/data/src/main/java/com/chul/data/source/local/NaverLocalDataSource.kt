package com.chul.data.source.local

import com.chul.data.model.MovieModel

internal interface NaverLocalDataSource {
    fun getMovieList(
        keyword: String,
        onSuccess: (movieList: List<MovieModel>) -> Unit
    )

    fun saveMovieList(
        keyword: String,
        movieList: List<MovieModel>
    )

    fun clearData()
}
package com.chul.data.source.remote

import com.chul.data.model.MovieModel

interface NaverRemoteDataSource {

    fun getMovieList(
        keyWord: String,
        onSuccess: (movieList: List<MovieModel>) -> Unit,
        onFailure: (t: Throwable) -> Unit
    )

}
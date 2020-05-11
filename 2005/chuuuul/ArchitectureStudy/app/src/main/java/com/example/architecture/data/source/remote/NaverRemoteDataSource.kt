package com.example.architecture.data.source.remote

import com.example.architecture.data.model.MovieModel

interface NaverRemoteDataSource {

    fun getMovieList(
        keyWord: String,
        onSuccess: (movies: List<MovieModel>) -> Unit,
        onFailure: (t: Throwable) -> Unit
    )

}
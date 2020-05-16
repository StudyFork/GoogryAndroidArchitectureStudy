package com.example.architecture.data.repository

import com.example.architecture.data.model.MovieModel
import com.example.architecture.data.source.remote.NaverRemoteDataSourceImpl

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
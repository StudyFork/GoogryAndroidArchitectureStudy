package com.example.architecture.data.source.remote

import android.content.Context
import com.example.architecture.data.model.MovieModel

interface NaverRemoteDataSource {

    fun getMovieList(
        context: Context,
        keyWord: String,
        onSuccess: (movies: List<MovieModel>) -> Unit,
        onFailure: (t: Throwable) -> Unit,
        saveMovieInLocal: (context: Context, keyword: String, movies: List<MovieModel>) -> Unit
    )

}
package com.example.architecture.data.source.local

import android.content.Context
import com.example.architecture.data.model.MovieModel

interface NaverLocalDataSource {
    fun getMovieList(
        context: Context,
        keyword: String,
        onSuccess: (movieList: List<MovieModel>) -> Unit
    )

    fun saveMovieList(
        context: Context,
        keyword: String,
        movieList: List<MovieModel>
    )

    fun clearData(context: Context)
}
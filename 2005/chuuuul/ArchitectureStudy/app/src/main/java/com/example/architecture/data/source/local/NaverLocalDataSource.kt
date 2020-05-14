package com.example.architecture.data.source.local

import android.content.Context
import com.example.architecture.data.model.MovieModel

interface NaverLocalDataSource {
    fun getMovieList(
        keyword: String,
        onSuccess: (movieList: List<MovieModel>) -> Unit
    )

    fun saveMovieList(
        keyword: String,
        movieList: List<MovieModel>
    )

    fun clearData(context: Context)
}
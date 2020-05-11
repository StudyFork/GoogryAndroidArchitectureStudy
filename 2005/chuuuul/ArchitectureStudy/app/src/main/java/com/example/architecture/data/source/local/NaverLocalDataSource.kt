package com.example.architecture.data.source.local

import android.content.Context
import com.example.architecture.data.model.MovieModel

interface NaverLocalDataSource {
    fun getMovieList(
        context: Context,
        keyword: String,
        onSuccess: (movies: List<MovieModel>) -> Unit
    )

    fun saveMovieList(
        context: Context,
        keyword: String,
        movies: List<MovieModel>
    )

    fun clearData(context: Context)
}
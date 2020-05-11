package com.example.architecture.data.repository

import android.content.Context
import com.example.architecture.data.model.MovieModel

interface NaverRepository {

    fun getMovieList(
        context: Context,
        keyword: String,
        onSuccess: (movies: List<MovieModel>) -> Unit,
        onFailure: (t: Throwable) -> Unit
    )

    fun clearCacheData(context: Context)
}
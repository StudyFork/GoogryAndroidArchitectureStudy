package com.project.architecturestudy.data.source.local

import android.content.Context
import com.project.architecturestudy.data.model.Movie

interface NaverMovieLocalDataSource {

    fun getMovieList(Success: (Movie.Items) -> Unit, Failure: (Throwable) -> Unit)

    fun saveMovieList(movieItem: ArrayList<Movie.Items>, context: Context)
}
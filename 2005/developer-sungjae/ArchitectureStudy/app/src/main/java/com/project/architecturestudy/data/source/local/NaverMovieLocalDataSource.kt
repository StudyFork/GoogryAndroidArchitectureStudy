package com.project.architecturestudy.data.source.local

import android.content.Context
import com.project.architecturestudy.data.model.Movie
import com.project.architecturestudy.data.source.local.room.MovieLocal

interface NaverMovieLocalDataSource {

    fun getMovieList(context: Context, Success: (ArrayList<MovieLocal>) -> Unit, Failure: (Throwable) -> Unit)
    fun saveMovieList(data: ArrayList<Movie.Items>, context: Context)
}
package com.project.architecturestudy.data.source.remote

import com.project.architecturestudy.components.RetrofitService
import com.project.architecturestudy.data.model.Movie

interface NaverMovieRemoteDataSource {

    val service: RetrofitService
    fun getMovieList(keyWord: String, Success: (ArrayList<Movie.Items>) -> Unit, Failure: (Throwable) -> Unit)
}
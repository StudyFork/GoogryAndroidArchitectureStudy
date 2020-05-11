package com.project.architecturestudy.data.source.remote

import com.project.architecturestudy.data.model.Movie

interface NaverMovieRemoteDataSource {

    fun getMovieList(query: String, Success: (Movie.Items) -> Unit, Failure: (Throwable) -> Unit)
}
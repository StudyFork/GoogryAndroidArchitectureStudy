package com.project.architecturestudy.data.source.remote

import com.project.architecturestudy.data.model.Movie

interface NaverMovieRemoteDataSource {

    fun getMovieList(keyWord: String, Success: (ArrayList<Movie.Items>) -> Unit, Failure: (Throwable) -> Unit)
}
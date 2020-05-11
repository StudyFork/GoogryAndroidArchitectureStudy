package com.project.architecturestudy.data.source.local

import com.project.architecturestudy.data.model.Movie

interface NaverMovieLocalDataSource {

    fun getMovieList(query: String, Success:((Movie.Items) -> Unit), Failure:((Throwable) -> Unit))

    fun saveMovieList(query: String)
}
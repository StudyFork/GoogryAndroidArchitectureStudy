package com.project.architecturestudy.data.repository

import com.project.architecturestudy.data.model.Movie
import com.project.architecturestudy.data.source.local.room.MovieLocal

interface NaverMovieRepository {

    fun getMovieList(
        keyWord: String,
        Success: (ArrayList<Movie.Items>) -> Unit,
        Failure: (t: Throwable) -> Unit
    )

    fun getCashedMovieList(Success: (ArrayList<MovieLocal>) -> Unit, Failure: (t: Throwable) -> Unit)
    fun saveMovieListToLocal(items: ArrayList<Movie.Items>)
    fun dispose()

}
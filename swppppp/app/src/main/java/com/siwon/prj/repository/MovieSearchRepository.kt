package com.siwon.prj.repository

import com.siwon.prj.datasource.MovieSearchDataSource
import com.siwon.prj.datasource.MovieSearchDataSourceImpl
import com.siwon.prj.model.Movie

interface MovieSearchRepository {
    fun searchMovies(query: String, success: (ArrayList<Movie>) -> Unit, fail: (Throwable) -> Unit)
}

class MovieSearchRepositoryImpl: MovieSearchRepository {
    private val dataSorce: MovieSearchDataSource =
        MovieSearchDataSourceImpl()
    override fun searchMovies(query: String, success: (ArrayList<Movie>) -> Unit, fail: (Throwable) -> Unit) =
        dataSorce.searchMovies(query, success, fail)
}
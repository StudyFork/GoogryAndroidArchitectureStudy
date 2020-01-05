package com.siwon.prj.repository

import com.siwon.prj.datasource.RemoteMovieSearchDataSource
import com.siwon.prj.datasource.RemoteMovieSearchDataSourceImpl
import com.siwon.prj.model.Movie

class MovieSearchRepositoryImpl: MovieSearchRepository {
    private val dataSorceRemote: RemoteMovieSearchDataSource =
        RemoteMovieSearchDataSourceImpl()
    override fun searchMovies(query: String, success: (ArrayList<Movie>) -> Unit, fail: (Throwable) -> Unit) =
        dataSorceRemote.searchMovies(query, success, fail)
}
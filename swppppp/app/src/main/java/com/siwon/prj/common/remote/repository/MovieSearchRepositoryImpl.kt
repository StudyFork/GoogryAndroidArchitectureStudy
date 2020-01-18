package com.siwon.prj.repository

import com.siwon.prj.common.remote.datasource.RemoteMovieSearchDataSource
import com.siwon.prj.common.remote.datasource.RemoteMovieSearchDataSourceImpl
import com.siwon.prj.common.model.Movie

class MovieSearchRepositoryImpl(/*val dataSorceRemote: RemoteMovieSearchDataSource*/): MovieSearchRepository {

    private val dataSorceRemote: RemoteMovieSearchDataSource = RemoteMovieSearchDataSourceImpl()

    override fun searchMovies(query: String, success: (ArrayList<Movie>) -> Unit, fail: (Throwable) -> Unit) =
        dataSorceRemote.searchMovies(query, success, fail)
}
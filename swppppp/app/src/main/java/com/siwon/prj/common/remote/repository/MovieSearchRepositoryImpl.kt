package com.siwon.prj.repository

import com.siwon.prj.common.model.Movie
import com.siwon.prj.common.remote.datasource.RemoteMovieSearchDataSource

class MovieSearchRepositoryImpl(val dataSorceRemote: RemoteMovieSearchDataSource): MovieSearchRepository {

    override fun searchMovies(query: String, success: (ArrayList<Movie>) -> Unit, fail: (Throwable) -> Unit) =
        dataSorceRemote.searchMovies(query, success, fail)
}
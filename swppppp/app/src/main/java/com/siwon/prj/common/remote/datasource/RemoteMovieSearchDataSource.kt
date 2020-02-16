package com.siwon.prj.common.remote.datasource

import com.siwon.prj.common.model.Movie
import com.siwon.prj.common.remote.datasource.service.MovieSearchService

interface RemoteMovieSearchDataSource {
    fun searchMovies(query: String, success: (ArrayList<Movie>) -> Unit, fail: (Throwable) -> Unit)
}


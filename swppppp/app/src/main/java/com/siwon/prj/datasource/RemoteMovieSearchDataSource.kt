package com.siwon.prj.datasource

import com.siwon.prj.model.Movie

interface RemoteMovieSearchDataSource {
    fun searchMovies(query: String, success: (ArrayList<Movie>) -> Unit, fail: (Throwable) -> Unit)
}


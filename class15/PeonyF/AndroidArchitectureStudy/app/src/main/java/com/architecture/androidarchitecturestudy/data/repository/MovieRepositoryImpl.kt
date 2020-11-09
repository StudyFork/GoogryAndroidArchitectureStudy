package com.architecture.androidarchitecturestudy.data.repository

import com.architecture.androidarchitecturestudy.data.model.MovieResponse
import com.architecture.androidarchitecturestudy.data.remote.MovieRemoteDataSourceImpl

class MovieRepositoryImpl : MovieRepository {
    override fun getMovieData(
        keyword: String,
        cnt: Int,
        onSuccess: (MovieResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        MovieRemoteDataSourceImpl.getMovieData(
            keyword = keyword,
            display = cnt,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }
}


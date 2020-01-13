package com.song2.myapplication.source

import com.song2.myapplication.source.remote.MovieRemoteDataSource

class MovieRepositoryImpl : MovieRepository {

    override fun getMovieData(
        keyword: String,
        cnt: Int,
        start : Int,
        onSuccess: (List<MovieData>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        MovieRemoteDataSource.getMovieData(
            keyword = keyword,
            display = cnt,
            start = start,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }
}
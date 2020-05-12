package com.sangjin.newproject.data.repository

import com.sangjin.newproject.data.model.Movie
import com.sangjin.newproject.data.source.remote.RemoteDataSourceImpl

class NaverMoviesRepositoryImpl(
    private val remoteDataSourceImpl: RemoteDataSourceImpl
) : NaverMoviesRepository {

    override fun getNaverMovies(
        query: String,
        onSuccess: (movies: List<Movie>) -> Unit,
        onFailure: (t: Throwable) -> Unit
    ) {
        remoteDataSourceImpl.getMovieData(query,
            onSuccess = { movies ->
                onSuccess(movies)
            },
            onFailure = { t ->
                onFailure(t)
            })
    }
}
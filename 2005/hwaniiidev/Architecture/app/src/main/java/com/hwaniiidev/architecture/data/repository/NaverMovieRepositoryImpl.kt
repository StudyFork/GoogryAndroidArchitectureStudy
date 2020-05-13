package com.hwaniiidev.architecture.data.repository

import com.hwaniiidev.architecture.data.source.remote.NaverMovieRemoteDataSourceImpl
import com.hwaniiidev.architecture.model.ResponseMovieSearchData

class NaverMovieRepositoryImpl : NaverMovieRepository {
    private val naverMovieRemoteSource = NaverMovieRemoteDataSourceImpl()

    override fun getRemoteMovies(
        query: String,
        //onSuccess: (movies: ArrayList<Item>) -> Unit,
        onSuccess: (response: ResponseMovieSearchData) -> Unit,
        onError: (errorMessage: String) -> Unit,
        onFailure: (t: Throwable) -> Unit
    ) {
        naverMovieRemoteSource.getRemoteMovies(
            query,
            onSuccess,
            onError,
            onFailure
        )
    }
}
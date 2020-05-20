package com.hwaniiidev.architecture.data.repository

import android.content.Context
import com.hwaniiidev.architecture.data.source.local.NaverMovieLocalDataSourceImpl
import com.hwaniiidev.architecture.data.source.remote.NaverMovieRemoteDataSourceImpl
import com.hwaniiidev.architecture.model.Item
import com.hwaniiidev.architecture.model.ResponseMovieSearchData

class NaverMovieRepositoryImpl : NaverMovieRepository {
    private val naverMovieRemoteSource = NaverMovieRemoteDataSourceImpl()
    private val naverMovieLocalDataSource = NaverMovieLocalDataSourceImpl()
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

    override fun cachingMovies(context: Context, query: String, movies: ArrayList<Item>) {
        naverMovieLocalDataSource.cachingMovies(
            context,
            query,
            movies
        )
    }

}
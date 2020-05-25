package com.hwaniiidev.architecture.data.repository

import android.content.Context
import com.hwaniiidev.architecture.data.source.local.NaverMovieLocalDataSourceImpl
import com.hwaniiidev.architecture.data.source.remote.NaverMovieRemoteDataSourceImpl
import com.hwaniiidev.architecture.model.Item
import com.hwaniiidev.architecture.model.ResponseMovieSearchData

class NaverMovieRepositoryImpl(context: Context) : NaverMovieRepository {
    private val mContext = context
    private val naverMovieRemoteSource = NaverMovieRemoteDataSourceImpl()
    private val naverMovieLocalDataSource = NaverMovieLocalDataSourceImpl(mContext)

    override fun getRemoteMovies(
        query: String,
        //onSuccess: (movies: ArrayList<Item>) -> Unit,
        onSuccess: (response: ResponseMovieSearchData) -> Unit,
        onError: (errorMessage: String) -> Unit,
        onFailure: (t: Throwable) -> Unit
    ) {
        naverMovieRemoteSource.getRemoteMovies(
            query = query,
            onSuccess = {response ->
                //local DB에 저장
                cachingMovies(query,response.items)
                onSuccess(response)
            },
            onError = onError,
            onFailure = onFailure
        )
    }

    override fun cachingMovies(query: String, movies: ArrayList<Item>) {
        naverMovieLocalDataSource.cachingMovies(
            query,
            movies
        )
    }
}
package com.hwaniiidev.architecture.data.repository

import android.content.Context
import android.util.Log
import com.hwaniiidev.architecture.data.source.local.NaverMovieLocalDataSourceImpl
import com.hwaniiidev.architecture.data.source.remote.NaverMovieRemoteDataSourceImpl
import com.hwaniiidev.architecture.model.Item
import com.hwaniiidev.architecture.model.ResponseMovieSearchData

class NaverMovieRepositoryImpl(context: Context) : NaverMovieRepository {
    private val mContext = context
    private val naverMovieRemoteSource = NaverMovieRemoteDataSourceImpl()
    private val naverMovieLocalDataSource = NaverMovieLocalDataSourceImpl(mContext)

    override fun searchMovies(
        query: String,
        onSuccess: (response: ResponseMovieSearchData) -> Unit,
        onError: (errorMessage: String) -> Unit,
        onFailure: (t: Throwable) -> Unit,
        onCached: (movies: List<Item>?) -> Unit
    ) {
        getCachedMovies(
            query = query,
            onCached = { movies ->

                //로컬에 검색한 쿼리의 영화리스트가 없을 경우 서버에 요청
                if (movies.isNullOrEmpty()) {
                    getRemoteMovies(
                        query = query,
                        onSuccess = onSuccess,
                        onError = onError,
                        onFailure = onFailure
                    )
                }
                //로컬에 있는 경우 바로 로컬에 있는 영화 리스트 출력
                else {
                    onCached(movies)
                }
            }
        )
    }

    override fun getRemoteMovies(
        query: String,
        onSuccess: (response: ResponseMovieSearchData) -> Unit,
        onError: (errorMessage: String) -> Unit,
        onFailure: (t: Throwable) -> Unit
    ) {
        naverMovieRemoteSource.getRemoteMovies(
            query = query,
            onSuccess = { response ->
                //local DB에 저장
                cachingMovies(query, response.items)
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

    override fun getCachedMovies(
        query: String,
        onCached: (movies: List<Item>?) -> Unit
    ) {
        naverMovieLocalDataSource.getCachedMovies(
            query = query,
            onCached = onCached
        )
    }
}
package com.example.architecture.data.repository

import com.example.architecture.data.model.MovieModel
import com.example.architecture.data.source.local.NaverLocalDataSource
import com.example.architecture.data.source.remote.NaverRemoteDataSource


class NaverRepositoryImpl(
    private val naverLocalDataSource: NaverLocalDataSource,
    private val naverRemoteDataSource: NaverRemoteDataSource
) : NaverRepository {


    override fun getMovieList(
        keyword: String,
        onSuccess: (movieList: List<MovieModel>) -> Unit,
        onFailure: (t: Throwable) -> Unit
    ) {

        naverLocalDataSource.getMovieList(keyword, onSuccess)

        naverRemoteDataSource.getMovieList(
            keyword,
            {
                onSuccess(it)
                saveMovieList(keyword, it)
            },
            onFailure
        )
    }


    override fun clearCacheData() {
        naverLocalDataSource.clearData()
    }

    override fun saveMovieList(
        keyword: String,
        movieList: List<MovieModel>
    ) {
        naverLocalDataSource.saveMovieList(keyword, movieList)
    }

}
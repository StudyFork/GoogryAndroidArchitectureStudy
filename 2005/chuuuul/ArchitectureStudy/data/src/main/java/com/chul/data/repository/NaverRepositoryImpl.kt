package com.chul.data.repository

import com.chul.data.model.MovieModel
import com.chul.data.source.local.NaverLocalDataSource
import com.chul.data.source.remote.NaverRemoteDataSource


internal class NaverRepositoryImpl(
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
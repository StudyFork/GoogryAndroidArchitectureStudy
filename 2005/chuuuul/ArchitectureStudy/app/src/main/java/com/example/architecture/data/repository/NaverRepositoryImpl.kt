package com.example.architecture.data.repository

import android.content.Context
import com.example.architecture.data.model.MovieModel
import com.example.architecture.data.source.local.NaverLocalDataSource
import com.example.architecture.data.source.local.NaverLocalDataSourceImpl
import com.example.architecture.data.source.remote.NaverRemoteDataSource
import com.example.architecture.data.source.remote.NaverRemoteDataSourceImpl


class NaverRepositoryImpl(context : Context) : NaverRepository {

    init {
        naverLocalDataSource = NaverLocalDataSourceImpl(context)
    }

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

    companion object {
        private val naverRemoteDataSource: NaverRemoteDataSource = NaverRemoteDataSourceImpl()
        private lateinit var naverLocalDataSource: NaverLocalDataSource
    }

}
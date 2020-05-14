package com.example.architecture.data.repository

import android.content.Context
import com.example.architecture.data.model.MovieModel
import com.example.architecture.data.source.local.NaverLocalDataSourceImpl
import com.example.architecture.data.source.remote.NaverRemoteDataSourceImpl

object NaverRepositoryImpl : NaverRepository {

    private val naverRemoteDataSourceImpl = NaverRemoteDataSourceImpl()
    private val naverLocalDataSourceImpl = NaverLocalDataSourceImpl()

    override fun getMovieList(
        context: Context,
        keyword: String,
        onSuccess: (movies: List<MovieModel>) -> Unit,
        onFailure: (t: Throwable) -> Unit
    ) {

        naverLocalDataSourceImpl.getMovieList(context, keyword, onSuccess)
        
        naverRemoteDataSourceImpl.getMovieList(
            context,
            keyword,
            onSuccess,
            onFailure,
            this::saveMovieList
        )
    }


    override fun clearCacheData(context: Context) {
        naverLocalDataSourceImpl.clearData(context)
    }

    private fun saveMovieList(
        context: Context,
        keyword: String,
        movies: List<MovieModel>
    ) {
        naverLocalDataSourceImpl.saveMovieList(context, keyword, movies)
    }

}
package com.example.architecture.data.repository

import com.example.architecture.data.model.MovieModel
import com.example.architecture.data.source.remote.NaverRemoteDataSourceImpl

object NaverRepositoryImpl : NaverRepository {

    private val naverRemoteDataSourceImpl: NaverRemoteDataSourceImpl by lazy {
        NaverRemoteDataSourceImpl()
    }

    override fun getMovieList(
        keyword: String,
        onSuccess: (movies: List<MovieModel>) -> Unit,
        onFailure: (t: Throwable) -> Unit
    ) {
        naverRemoteDataSourceImpl.getMovieList(keyword, onSuccess, onFailure)
    }


}
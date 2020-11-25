package com.example.hw2_project.data.repository

import com.example.hw2_project.data.api.NaverMovieData
import com.example.hw2_project.data.local.MovieLocalDataSourceImpl
import com.example.hw2_project.data.remote.MovieRemoteDataSourceImpl

class MovieRepositoryImpl : MovieRepository{

    private val remoteMovieDataSourceImp = MovieRemoteDataSourceImpl()
    private val localMovieLocalDataSourceImpl = MovieLocalDataSourceImpl()

    override fun getMovieList(
        query: String,
        success: (NaverMovieData.NaverMovieResponse) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        remoteMovieDataSourceImp.getMovieFromRemote(
            query,
            success,
            fail
        )
    }

    override fun saveQuery(query: String) {
        localMovieLocalDataSourceImpl.saveQuery(query)
    }

    override fun getSavedQuery(): List<String> {
        return localMovieLocalDataSourceImpl.getSavedQuery()
    }
}
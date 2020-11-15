package com.example.hw2_project.data.repository

import com.example.hw2_project.data.MovieList
import com.example.hw2_project.data.remote.MovieRemoteDataSourceImpl

class MovieRepositoryImpl : MovieRepository{

    private val remoteMovieDataSourceImp = MovieRemoteDataSourceImpl()

    override fun getMovieList(
        query: String,
        success: (MovieList) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        remoteMovieDataSourceImp.getMovieFromRemote(
            query,
            success,
            fail
        )
    }
}
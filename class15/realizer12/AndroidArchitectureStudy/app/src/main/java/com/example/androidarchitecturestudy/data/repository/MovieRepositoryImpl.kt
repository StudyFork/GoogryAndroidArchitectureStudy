package com.example.androidarchitecturestudy.data.repository

import com.example.androidarchitecturestudy.data.GetMovieInfo
import com.example.androidarchitecturestudy.data.datasource.RemoteMovieDataImpl

class MovieRepositoryImpl() :MovieRepository{

    private val remoteMovieDataSource = RemoteMovieDataImpl()

    override fun getMovieSearchResult(
        movieName: String,
        onSuccess: (GetMovieInfo.MovieList) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {

        remoteMovieDataSource.getMovieSearchResult(
            movieName, onSuccess, onFailure
        )
    }

}
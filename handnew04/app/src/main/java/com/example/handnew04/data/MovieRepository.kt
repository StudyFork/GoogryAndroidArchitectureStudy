package com.example.handnew04.data

import com.example.handnew04.data.local.MovieLocalDataResource
import com.example.handnew04.data.local.MovieLocalDataResourceImpl
import com.example.handnew04.data.remote.MovieRemoteDataResource
import com.example.handnew04.data.remote.MovieRemoteDataResourceImpl

class MovieRepository {
    private var movieLocalDataResource: MovieLocalDataResource = MovieLocalDataResourceImpl()
    private var movieRemoteDataResource: MovieRemoteDataResource = MovieRemoteDataResourceImpl()

    fun getMovieData(query: String, success: (NaverMovieResponse) -> Unit, fail: (Throwable) -> Unit) =
        movieRemoteDataResource.getMovieData(query, success, fail)
}
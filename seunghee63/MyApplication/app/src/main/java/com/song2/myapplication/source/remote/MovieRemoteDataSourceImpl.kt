package com.song2.myapplication.source.remote

import com.song2.myapplication.source.MovieDataResponse


interface MovieRemoteDataSourceImpl {

    fun getMovieData(
        keyword: String,
        display: Int,
        onSuccess: (MovieDataResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    )

}
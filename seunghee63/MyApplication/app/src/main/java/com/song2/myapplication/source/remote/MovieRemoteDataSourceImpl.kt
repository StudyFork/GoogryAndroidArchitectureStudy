package com.song2.myapplication.source.remote

import com.song2.myapplication.source.MovieData


interface MovieRemoteDataSourceImpl {

    fun getMovieData(
        keyword: String,
        display: Int,
        onSuccess: (List<MovieData>) -> Unit,
        onFailure: (Throwable) -> Unit
    )

}
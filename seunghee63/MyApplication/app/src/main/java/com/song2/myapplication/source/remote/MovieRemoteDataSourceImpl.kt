package com.song2.myapplication.source.remote

import com.song2.myapplication.source.MovieData


interface MovieRemoteDataSource {

    fun getMovieData(
        keyword: String,
        display: Int,
        start : Int,
        onSuccess: (List<MovieData>) -> Unit,
        onFailure: (Throwable) -> Unit
    )

}
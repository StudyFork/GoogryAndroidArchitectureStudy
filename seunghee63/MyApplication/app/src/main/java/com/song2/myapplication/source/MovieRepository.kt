package com.song2.myapplication.source


interface MovieRepository {

    fun getMovieData(
        keyword: String,
        cnt: Int,
        start : Int,
        onSuccess: (List<MovieData>) -> Unit,
        onFailure: (Throwable) -> Unit
    )
}
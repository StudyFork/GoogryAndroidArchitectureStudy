package com.lllccww.data.source.remote

import com.lllccww.data.model.MovieItem

interface MovieRemoteDataSource {

    fun getMovieList(
        keyWord: String,
        onSuccess: (List<MovieItem>) -> Unit,
        onFailure: (e: Throwable) -> Unit
    )


}
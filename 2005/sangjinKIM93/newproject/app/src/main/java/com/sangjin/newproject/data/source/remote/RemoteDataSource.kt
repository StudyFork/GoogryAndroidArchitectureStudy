package com.sangjin.newproject.data.source.remote

import com.sangjin.newproject.data.model.Movie

interface RemoteDataSource {

    fun getMovieData(
        query: String,
        onSuccess: ((movies: List<Movie>) -> Unit),
        onFailure: ((t: Throwable) -> Unit)
    )

}
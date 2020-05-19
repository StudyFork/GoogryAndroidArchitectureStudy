package com.sangjin.newproject.data.repository

import com.sangjin.newproject.data.model.Movie
import com.sangjin.newproject.data.source.local.RoomDB

interface NaverMoviesRepository {

    fun getNaverMovies(
        query: String,
        onSuccess: ((movies: List<Movie>) -> Unit),
        onFailure: ((t: Throwable) -> Unit)
    )

}
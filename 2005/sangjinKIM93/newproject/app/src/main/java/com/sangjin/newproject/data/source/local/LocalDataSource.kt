package com.sangjin.newproject.data.source.local

import com.sangjin.newproject.data.model.Movie

interface LocalDataSource {

    fun getMovieData(
        query: String,
        onSuccess: ((movies: List<Movie>) -> Unit)
    )

    fun saveMovieData(
        movies: List<Movie>
    )

}
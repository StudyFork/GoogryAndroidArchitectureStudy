package com.kyudong.data.local

import com.kyudong.data.model.Movie

interface MovieLocalDataSource {
    fun getMovieList(query: String): List<Movie>
    fun saveMovieList(movieList: List<Movie>)
}
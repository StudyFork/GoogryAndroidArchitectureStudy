package com.example.kyudong3.data.local

import com.example.kyudong3.data.model.Movie

interface MovieLocalDataSource {
    fun getMovieList(query: String): List<Movie>
    fun saveMovieList(movieList: List<Movie>)
}
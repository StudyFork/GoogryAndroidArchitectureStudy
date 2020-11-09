package com.example.androidarchitecturestudy.data.local

import com.example.androidarchitecturestudy.data.model.Movie

interface LocalMovieData {
    fun saveMovieData(movie: List<Movie>)

    fun getMovieData(): List<Movie>?
}
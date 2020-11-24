package com.example.androidarchitecturestudy.data.local

import com.example.androidarchitecturestudy.data.model.Movie

interface NaverLocalDataSource {
    fun saveMovieData(movie: List<Movie>)

    fun getMovieData(): List<Movie>?

    fun saveMovieTitle(title: String)

    fun getMovieTitleList(): List<String>
}
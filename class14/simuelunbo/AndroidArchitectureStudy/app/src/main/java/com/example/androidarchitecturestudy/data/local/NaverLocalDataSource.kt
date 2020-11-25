package com.example.androidarchitecturestudy.data.local

import com.example.androidarchitecturestudy.data.model.Movie
import com.example.androidarchitecturestudy.data.model.QueryHistory

interface NaverLocalDataSource {
    fun saveMovieData(movie: List<Movie>)

    fun getMovieData(): List<Movie>?

    fun saveMovieQuery(title: String)

    fun getMovieQueryList(): List<QueryHistory>
}
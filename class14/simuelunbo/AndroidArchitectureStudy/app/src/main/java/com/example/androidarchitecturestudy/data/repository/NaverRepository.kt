package com.example.androidarchitecturestudy.data.repository

import com.example.androidarchitecturestudy.data.model.Movie
import com.example.androidarchitecturestudy.data.model.MovieData
import com.example.androidarchitecturestudy.data.model.QueryHistory

interface NaverRepository {

    fun getSearchMovieList(
        query: String,
        success: (MovieData) -> Unit,
        failed: (String) -> Unit
    )

    fun saveMovieData(movie: List<Movie>)

    fun getMovieData(): List<Movie>?

    fun saveMovieQuery(title: String)

    fun getMovieQueryList(): List<QueryHistory>?

}
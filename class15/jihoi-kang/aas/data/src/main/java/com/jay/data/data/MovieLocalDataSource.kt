package com.jay.data.data

import com.jay.data.model.Movie
import com.jay.data.model.SearchHistory

interface MovieLocalDataSource {

    fun insertMovies(query: String, movies: List<Movie>)
    fun getMovies(): List<Movie>
    fun clearMovies()
    fun getSearchHistories(): List<SearchHistory>

}
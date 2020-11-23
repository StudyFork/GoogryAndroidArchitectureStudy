package com.jay.aas.data

import com.jay.aas.model.Movie
import com.jay.aas.model.SearchHistory

interface MovieLocalDataSource {

    fun insertMovies(query: String, movies: List<Movie>)
    fun getMovies(): List<Movie>
    fun clearMovies()
    fun getSearchHistories(): List<SearchHistory>

}
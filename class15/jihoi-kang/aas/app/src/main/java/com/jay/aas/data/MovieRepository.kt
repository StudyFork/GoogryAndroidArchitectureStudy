package com.jay.aas.data

import com.jay.aas.model.Movie
import com.jay.aas.model.SearchHistory

interface MovieRepository {

    suspend fun getMovies(): List<Movie>
    suspend fun getSearchMovies(query: String): List<Movie>
    fun getSearchHistories(): List<SearchHistory>

}
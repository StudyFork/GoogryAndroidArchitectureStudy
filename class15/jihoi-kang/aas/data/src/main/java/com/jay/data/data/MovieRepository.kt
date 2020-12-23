package com.jay.data.data

import com.jay.data.model.Movie
import com.jay.data.model.SearchHistory

interface MovieRepository {

    suspend fun getMovies(): List<Movie>
    suspend fun getSearchMovies(query: String): List<Movie>
    fun getSearchHistories(): List<SearchHistory>

}
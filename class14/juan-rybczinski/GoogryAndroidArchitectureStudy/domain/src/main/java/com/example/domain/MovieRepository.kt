package com.example.domain

import com.example.domain.model.Movie
import com.example.domain.model.RecentSearch

interface MovieRepository {
    suspend fun searchMovies(search: String): List<Movie>

    suspend fun searchRecent(): List<RecentSearch>

    suspend fun insertRecent(recentSearch: RecentSearch)
}
package com.example.googryandroidarchitecturestudy.data.repository

import com.example.googryandroidarchitecturestudy.domain.Movie
import com.example.googryandroidarchitecturestudy.domain.RecentSearch

interface MovieRepository {
    suspend fun searchMovies(search: String): List<Movie>

    suspend fun searchRecent(): List<RecentSearch>

    suspend fun insertRecent(recentSearch: RecentSearch)
}
package com.example.googryandroidarchitecturestudy.data.local

import com.example.googryandroidarchitecturestudy.database.DatabaseMovie
import com.example.googryandroidarchitecturestudy.database.DatabaseRecentSearch

interface MovieLocalDataSource {
    suspend fun searchMoviesFromLocal(search: String): List<DatabaseMovie>

    suspend fun insertAll(movies: List<DatabaseMovie>)

    suspend fun searchRecentFromLocal(): List<DatabaseRecentSearch>

    suspend fun insertRecentToLocal(recentSearch: DatabaseRecentSearch)
}
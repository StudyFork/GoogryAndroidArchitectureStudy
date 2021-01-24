package com.example.data.source

import com.example.data.model.database.DatabaseMovie
import com.example.data.model.database.DatabaseRecentSearch

interface MovieLocalDataSource {
    suspend fun searchMoviesFromLocal(search: String): List<DatabaseMovie>

    suspend fun insertAll(movies: List<DatabaseMovie>)

    suspend fun searchRecentFromLocal(): List<DatabaseRecentSearch>

    suspend fun insertRecentToLocal(recentSearch: DatabaseRecentSearch)
}
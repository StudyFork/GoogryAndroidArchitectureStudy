package com.example.googryandroidarchitecturestudy.data.repository

import com.example.googryandroidarchitecturestudy.data.local.MovieLocalDataSource
import com.example.googryandroidarchitecturestudy.data.remote.MovieRemoteDataSource
import com.example.googryandroidarchitecturestudy.database.asDomainModel
import com.example.googryandroidarchitecturestudy.domain.Movie
import com.example.googryandroidarchitecturestudy.domain.RecentSearch
import com.example.googryandroidarchitecturestudy.domain.asDatabaseModel
import com.example.googryandroidarchitecturestudy.network.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource
) : MovieRepository {
    override suspend fun searchMovies(search: String): List<Movie> = withContext(Dispatchers.IO) {
        remoteDataSource.searchMoviesFromRemote(search).asDomainModel()
    }

    override suspend fun searchRecent(): List<RecentSearch> = withContext(Dispatchers.IO) {
        localDataSource.searchRecentFromLocal().asDomainModel()
    }

    override suspend fun insertRecent(recentSearch: RecentSearch) {
        withContext(Dispatchers.IO) {
            localDataSource.insertRecentToLocal(recentSearch.asDatabaseModel())
        }
    }
}


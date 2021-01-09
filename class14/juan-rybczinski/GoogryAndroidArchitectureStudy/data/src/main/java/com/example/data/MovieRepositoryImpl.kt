package com.example.data

import com.example.data.model.database.asDatabaseModel
import com.example.data.model.database.asDomainModel
import com.example.data.model.network.asDomainModel
import com.example.data.source.MovieLocalDataSource
import com.example.data.source.MovieRemoteDataSource
import com.example.domain.MovieRepository
import com.example.domain.model.Movie
import com.example.domain.model.RecentSearch
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


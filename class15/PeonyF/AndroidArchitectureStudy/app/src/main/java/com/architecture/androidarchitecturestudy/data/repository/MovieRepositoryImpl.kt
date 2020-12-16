package com.architecture.androidarchitecturestudy.data.repository

import com.architecture.androidarchitecturestudy.data.local.MovieLocalDataSource
import com.architecture.androidarchitecturestudy.data.model.MovieResponse
import com.architecture.androidarchitecturestudy.data.model.SearchHistoryEntity
import com.architecture.androidarchitecturestudy.data.remote.MovieRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject
import javax.inject.Singleton

class MovieRepositoryImpl @Inject constructor(
    private val localMovieRepository: MovieLocalDataSource,
    private val remoteMovieRepository: MovieRemoteDataSource
) : MovieRepository {
    override fun getMovieData(
        keyword: String,
        display: Int,
        onSuccess: (MovieResponse) -> Unit,
        onFailure: (Throwable) -> Unit,
    ) {
        return remoteMovieRepository.getMovieData(
            keyword = keyword,
            display = display, {
                onSuccess(it)
                saveSearchHistory(keyword)
            },
            onFailure = onFailure
        )
    }

    override fun saveSearchHistory(keyword: String) {
        localMovieRepository.saveSearchHistory(keyword)
    }

    override fun getSearchHistoryList(): List<SearchHistoryEntity> {
        return localMovieRepository.getSearchHistoryList()
    }
}

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRepository(repositoryImpl: MovieRepositoryImpl): MovieRepository
}
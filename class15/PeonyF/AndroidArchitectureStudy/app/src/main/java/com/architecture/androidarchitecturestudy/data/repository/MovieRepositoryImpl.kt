package com.architecture.androidarchitecturestudy.data.repository

import com.architecture.androidarchitecturestudy.data.local.MovieLocalDataSourceImpl
import com.architecture.androidarchitecturestudy.data.model.MovieResponse
import com.architecture.androidarchitecturestudy.data.model.SearchHistoryEntity
import com.architecture.androidarchitecturestudy.data.remote.MovieRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject
import javax.inject.Singleton

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSourceImpl: MovieRemoteDataSourceImpl,
    private val movieLocalDataSourceImpl: MovieLocalDataSourceImpl
) : MovieRepository {
    override fun getMovieData(
        keyword: String,
        display: Int,
        onSuccess: (MovieResponse) -> Unit,
        onFailure: (Throwable) -> Unit,
    ) {
        return movieRemoteDataSourceImpl.getMovieData(
            keyword = keyword,
            display = display, {
                onSuccess(it)
                saveSearchHistory(keyword)
            },
            onFailure = onFailure
        )
    }

    override fun saveSearchHistory(keyword: String) {
        movieLocalDataSourceImpl.saveSearchHistory(keyword)
    }

    override fun getSearchHistoryList(): List<SearchHistoryEntity> {
        return movieLocalDataSourceImpl.getSearchHistoryList()
    }
}

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRepository(repositoryImpl: MovieRepositoryImpl): MovieRepository
}
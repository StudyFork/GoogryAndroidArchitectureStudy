package com.example.datamodule.data.repository

import com.example.datamodule.data.model.ApiResult
import com.example.datamodule.data.source.local.LocalDataSource
import com.example.datamodule.data.source.remote.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

class MovieSearchRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : MovieSearchRepository {

    override fun getMovies(query: String): Single<ApiResult> {
        return remoteDataSource.getMovies(query)
            .doOnSuccess { localDataSource.saveQuery(query) }
    }

    override fun getSavedQueries(): Single<List<String>> = localDataSource.getSavedQuery()

    override fun onDestroy() {
        localDataSource.onDestroy()
    }
}

@Module
@InstallIn(ApplicationComponent::class)
abstract class MovieSearchRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindMovieSearchRepository(MovieSearchRepositoryImpl: MovieSearchRepositoryImpl): MovieSearchRepository
}
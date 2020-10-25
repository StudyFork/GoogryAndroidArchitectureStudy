package com.hong.data.repository

import com.hong.data.source.local.LocalDataSource
import com.hong.data.source.local.entity.MovieInfo
import com.hong.architecturestudy.data.source.remote.RemoteDataSource
import com.hong.data.model.MovieData
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject
import javax.inject.Singleton

class RepositoryDataSourceImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : RepositoryDataSource {

    override fun getMovieList(
        query: String,
        onSuccess: (List<MovieData>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        remoteDataSource.getMovieList(
            query,
            {
                onSuccess(it)
                saveRecentSearchQuery(query)
            }, onFailure
        )
    }

    override fun saveRecentSearchQuery(keyword: String) =
        localDataSource.saveRecentSearchQuery(keyword)


    override fun loadRecentSearchQuery(): List<MovieInfo> =
        localDataSource.loadRecentSearchQuery()
}

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(repositoryDataSourceImpl: RepositoryDataSourceImpl): RepositoryDataSource
}
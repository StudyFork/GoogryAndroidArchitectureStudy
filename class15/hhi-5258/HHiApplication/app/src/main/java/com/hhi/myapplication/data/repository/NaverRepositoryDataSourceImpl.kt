package com.hhi.myapplication.data.repository

import com.hhi.myapplication.data.local.NaverLocalDataSourceImpl
import com.hhi.myapplication.data.model.MovieData
import com.hhi.myapplication.data.remote.NaverRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject
import javax.inject.Singleton

class NaverRepositoryDataSourceImpl @Inject constructor(
    private val naverRemoteDataSource: NaverRemoteDataSourceImpl,
    private val naverLocalDataSource: NaverLocalDataSourceImpl
) : NaverRepositoryDataSource {

    override fun searchMovies(
        query: String,
        success: (MovieData.Response) -> Unit,
        failed: (Throwable) -> Unit
    ) {
        naverRemoteDataSource.searchMovies(query, success, failed)
    }

    override fun saveQuery(query: String) {
        naverLocalDataSource.saveQuery(query)
    }

    override fun getQueryList(): List<String> = naverLocalDataSource.getQueryList()
}

@InstallIn(ApplicationComponent::class)
@Module
abstract class NaverRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindNaverRepository(naverRepositoryDataSourceImpl: NaverRepositoryDataSourceImpl): NaverRepositoryDataSource
}
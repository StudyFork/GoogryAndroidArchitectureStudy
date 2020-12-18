package com.wybh.androidarchitecturestudy.model.repository

import com.wybh.androidarchitecturestudy.data.ResponseCinemaData
import com.wybh.androidarchitecturestudy.model.local.NaverLocalDataSourceImpl
import com.wybh.androidarchitecturestudy.model.remote.NaverRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

class RepositoryImpl @Inject constructor(
    private val naverRemoteDataSource: NaverRemoteDataSourceImpl,
    private val naverLocalDataSource: NaverLocalDataSourceImpl
) : Repository {

    override fun searchCinema(query: String): Single<ResponseCinemaData> {
        naverLocalDataSource.saveSearchWord(query)
        return naverRemoteDataSource.searchCinema(query)
    }

    override fun getSearchWord(): String? {
        return naverLocalDataSource.getSearchWord()
    }
}

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRepository(repositoryImpl: RepositoryImpl): Repository
}
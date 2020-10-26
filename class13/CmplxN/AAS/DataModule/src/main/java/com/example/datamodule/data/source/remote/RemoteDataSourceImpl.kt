package com.example.datamodule.data.source.remote

import com.example.datamodule.data.model.ApiResult
import com.example.datamodule.data.network.RetrofitManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

class RemoteDataSourceImpl @Inject constructor(
    private val retrofitManager: RetrofitManager
) : RemoteDataSource {
    override fun getMovies(query: String): Single<ApiResult> =
        retrofitManager.naverMoviesApi.getMovies(query)
}

@Module
@InstallIn(ApplicationComponent::class)
abstract class RemoteDataSourceModule {
    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource
}
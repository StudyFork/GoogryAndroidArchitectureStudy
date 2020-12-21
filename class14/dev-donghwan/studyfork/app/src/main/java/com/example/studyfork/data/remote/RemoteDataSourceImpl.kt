package com.example.studyfork.data.remote

import com.example.studyfork.api.NaverApi
import com.example.studyfork.data.model.MovieSearchResponse
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.reactivex.Single
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val naverApi: NaverApi
) : RemoteDataSource {
    override fun searchMovie(query: String): Single<MovieSearchResponse> {
        return naverApi.searchMovie(query)
    }
}

@InstallIn(ApplicationComponent::class)
@Module
abstract class RemoteDataSourceModule {
    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource
}
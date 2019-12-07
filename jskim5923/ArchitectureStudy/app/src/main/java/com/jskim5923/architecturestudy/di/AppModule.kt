package com.jskim5923.architecturestudy.di

import com.jskim5923.architecturestudy.api.ApiModule
import com.jskim5923.architecturestudy.api.CoinApi
import com.jskim5923.architecturestudy.model.data.source.RemoteDataSource
import com.jskim5923.architecturestudy.model.data.source.RemoteDataSourceImpl
import com.jskim5923.architecturestudy.model.data.source.Repository
import com.jskim5923.architecturestudy.model.data.source.RepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [ApiModule::class]
)
class AppModule {
    @Singleton
    @Provides
    fun provideRemoteSource(api: CoinApi): RemoteDataSource = RemoteDataSourceImpl(api)

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSource): Repository =
        RepositoryImpl(remoteDataSource)
}
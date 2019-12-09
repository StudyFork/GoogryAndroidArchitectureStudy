package com.example.seonoh.seonohapp.di

import com.example.seonoh.seonohapp.datasource.CoinDataSourceImpl
import com.example.seonoh.seonohapp.network.Api
import com.example.seonoh.seonohapp.repository.CoinRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun proviedeCoinRepository(coinApi: Api): CoinRepositoryImpl = CoinRepositoryImpl(coinApi)

    @Provides
    @Singleton
    fun proviedeCoinDatasource(coinApi: Api): CoinDataSourceImpl = CoinDataSourceImpl(coinApi)

}
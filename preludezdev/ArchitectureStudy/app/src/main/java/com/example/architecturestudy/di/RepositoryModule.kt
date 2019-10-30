package com.example.architecturestudy.di

import com.example.architecturestudy.data.source.CoinsDataSource
import com.example.architecturestudy.data.source.CoinsRepository
import com.example.architecturestudy.data.source.CoinsRepositoryImpl
import com.example.architecturestudy.data.source.local.CoinsLocalDataSource
import com.example.architecturestudy.data.source.remote.CoinsRemoteDataSource
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single { CoinsRepositoryImpl(get(named("Remote")), get(named("Local"))) as CoinsRepository }
    single(named("Remote")) { CoinsRemoteDataSource(get()) as CoinsDataSource }
    single(named("Local")) { CoinsLocalDataSource() as CoinsDataSource }
}
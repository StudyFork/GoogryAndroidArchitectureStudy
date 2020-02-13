package com.jay.architecturestudy.di

import com.jay.architecturestudy.data.repository.NaverSearchRepository
import com.jay.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.jay.architecturestudy.data.source.local.NaverSearchLocalDataSource
import com.jay.architecturestudy.data.source.local.NaverSearchLocalDataSourceImpl
import com.jay.architecturestudy.data.source.remote.NaverSearchRemoteDataSource
import com.jay.architecturestudy.data.source.remote.NaverSearchRemoteDataSourceImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<NaverSearchLocalDataSource> { NaverSearchLocalDataSourceImpl(get(), get()) }
    single<NaverSearchRemoteDataSource> { NaverSearchRemoteDataSourceImpl(get()) }
    single<NaverSearchRepository> { NaverSearchRepositoryImpl(get(), get()) }
}

package com.egiwon.architecturestudy.di

import com.egiwon.architecturestudy.data.NaverDataRepository
import com.egiwon.architecturestudy.data.NaverDataRepositoryImpl
import com.egiwon.architecturestudy.data.source.NaverDataSource
import com.egiwon.architecturestudy.data.source.local.ContentDataBase
import com.egiwon.architecturestudy.data.source.local.NaverLocalDataSource
import com.egiwon.architecturestudy.data.source.remote.NaverRemoteDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataSourceModule = module {
    single { ContentDataBase.getInstance(context = androidContext()).contentDao() }
    single<NaverDataSource.Local> { NaverLocalDataSource(get()) }
    single<NaverDataSource.Remote> { NaverRemoteDataSource(get(named("remote"))) }
    single<NaverDataRepository>(named("repo")) { NaverDataRepositoryImpl(get(), get()) }
}
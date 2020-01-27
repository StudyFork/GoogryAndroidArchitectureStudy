package com.practice.achitecture.myproject.di

import android.content.Context
import com.practice.achitecture.myproject.data.source.NaverDataSource
import com.practice.achitecture.myproject.data.source.NaverRepository
import com.practice.achitecture.myproject.data.source.local.NaverDatabase
import com.practice.achitecture.myproject.data.source.local.NaverLocalDataSourceImpl
import com.practice.achitecture.myproject.data.source.remote.NaverRemoteDataSourceImpl
import com.practice.achitecture.myproject.network.RetrofitClient
import com.practice.achitecture.myproject.util.AppExecutors
import common.NAVER_API_BASE_URL
import org.koin.core.qualifier.named
import org.koin.dsl.module


val naverRepositoryModule = module {
    single { RetrofitClient(NAVER_API_BASE_URL).makeRetrofitServiceForNaver() }
    single<NaverDataSource>(named("naverRemoteData")) { NaverRemoteDataSourceImpl(get()) }
    single { AppExecutors() }
    single { NaverDatabase.getInstance(get()).naverDao() }
    single<NaverDataSource>(named("naverLocalData")) { NaverLocalDataSourceImpl(get(), get(), (get() as Context).cacheDir.path) }
    single<NaverDataSource>(named("naverRepository")) { NaverRepository(get(named("naverRemoteData")), get(named("naverLocalData"))) }
}
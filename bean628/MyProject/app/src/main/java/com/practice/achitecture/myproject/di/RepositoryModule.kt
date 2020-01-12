package com.practice.achitecture.myproject.di

import android.content.Context
import com.practice.achitecture.myproject.data.source.NaverRepository
import com.practice.achitecture.myproject.data.source.local.NaverDatabase
import com.practice.achitecture.myproject.data.source.local.NaverLocalDataSourceImpl
import com.practice.achitecture.myproject.data.source.remote.NaverRemoteDataSourceImpl
import com.practice.achitecture.myproject.network.RetrofitClient
import com.practice.achitecture.myproject.util.AppExecutors
import common.NAVER_API_BASE_URL
import org.koin.dsl.module


val naverRepositoryModule = module {
    single { RetrofitClient(NAVER_API_BASE_URL).makeRetrofitServiceForNaver() }
    single { NaverRemoteDataSourceImpl(get()) }
    single { AppExecutors() }
    single { NaverDatabase.getInstance(get()).naverDao() }
    single { NaverLocalDataSourceImpl(get(), get(), (get() as Context).cacheDir.path) }
    single { NaverRepository(get(), get()) }
}
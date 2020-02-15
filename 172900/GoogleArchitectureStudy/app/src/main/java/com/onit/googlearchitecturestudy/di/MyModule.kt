package com.onit.googlearchitecturestudy.di

import com.onit.googlearchitecturestudy.Config
import com.onit.googlearchitecturestudy.NaverApiInterceptor
import com.onit.googlearchitecturestudy.NaverApiService
import com.onit.googlearchitecturestudy.data.repository.MovieRepository
import com.onit.googlearchitecturestudy.data.repository.MovieRepositoryImpl
import com.onit.googlearchitecturestudy.data.source.remote.NaverApiRemoteDataSource
import com.onit.googlearchitecturestudy.data.source.remote.NaverApiRemoteDataSourceImpl
import com.onit.googlearchitecturestudy.ui.main.MainViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    viewModel { MainViewModel(get()) }
    single<MovieRepository> { MovieRepositoryImpl(get()) }
    single<NaverApiRemoteDataSource> { NaverApiRemoteDataSourceImpl(get()) }
    single<NaverApiService> {
        Retrofit.Builder().apply {
            baseUrl(Config.NAVER_API_BASE_URL)
            addConverterFactory(GsonConverterFactory.create())
            client(OkHttpClient.Builder().apply {
                interceptors().add(NaverApiInterceptor())
            }.build())
        }.build().create(NaverApiService::class.java)
    }
}
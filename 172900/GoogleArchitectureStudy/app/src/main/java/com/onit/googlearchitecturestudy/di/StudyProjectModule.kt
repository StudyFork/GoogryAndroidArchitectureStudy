package com.onit.googlearchitecturestudy.di

import com.onit.googlearchitecturestudy.Config
import com.onit.googlearchitecturestudy.NaverApiInterceptor
import com.onit.googlearchitecturestudy.NaverApiService
import com.onit.googlearchitecturestudy.data.repository.MovieRepository
import com.onit.googlearchitecturestudy.data.repository.MovieRepositoryImpl
import com.onit.googlearchitecturestudy.data.source.remote.NaverApiRemoteDataSource
import com.onit.googlearchitecturestudy.data.source.remote.NaverApiRemoteDataSourceImpl
import com.onit.googlearchitecturestudy.ui.main.MainContract
import com.onit.googlearchitecturestudy.ui.main.MainPresenter
import okhttp3.OkHttpClient
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val applicationModule = module {
    single { getNaverApiService() }
    single { NaverApiRemoteDataSourceImpl(get()) } bind NaverApiRemoteDataSource::class
    single { MovieRepositoryImpl(get()) } bind MovieRepository::class

    factory { (view: MainContract.View) ->
        MainPresenter(
            view,
            get()
        )
    } bind MainContract.Presenter::class
}

fun getNaverApiService(): NaverApiService {
    return Retrofit.Builder().apply {
        baseUrl(Config.NAVER_API_BASE_URL)
        addConverterFactory(GsonConverterFactory.create())
        client(OkHttpClient.Builder().apply {
            interceptors().add(NaverApiInterceptor())
        }.build())
    }.build().create(NaverApiService::class.java)
}
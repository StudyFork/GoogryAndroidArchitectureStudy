package com.siwon.prj.di

import com.siwon.prj.datasource.RemoteMovieSearchDataSource
import com.siwon.prj.datasource.RemoteMovieSearchDataSourceImpl
import com.siwon.prj.datasource.service.MovieSearchService
import com.siwon.prj.model.ApiInfo
import com.siwon.prj.repository.MovieSearchRepository
import com.siwon.prj.repository.MovieSearchRepositoryImpl
import com.siwon.prj.view.presenter.MainContract
import com.siwon.prj.view.presenter.MainPresenter
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val remotemodule = module {
    single {
        GsonConverterFactory.create() as Converter.Factory
    }
    single {
        Retrofit.Builder()
            .baseUrl(ApiInfo.BASE_URL)
            .addConverterFactory(get())
            .build()
            .create(MovieSearchService::class.java)
    }
    single<RemoteMovieSearchDataSource>{
        RemoteMovieSearchDataSourceImpl(get())
    }

    single<MovieSearchRepository>{
        MovieSearchRepositoryImpl(get())
    }

    factory<MainContract.Presenter> {
        (view: MainContract.View) -> MainPresenter(view, get())
    } bind MainContract.Presenter::class
}
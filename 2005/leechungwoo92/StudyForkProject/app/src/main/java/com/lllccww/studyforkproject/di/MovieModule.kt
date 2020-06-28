package com.lllccww.studyforkproject.di

import com.lllccww.studyforkproject.ApiService
import com.lllccww.studyforkproject.Constants
import com.lllccww.studyforkproject.data.repository.NaverMovieRepository
import com.lllccww.studyforkproject.data.repository.NaverMovieRepositoryImpl
import com.lllccww.studyforkproject.data.source.remote.MovieRemoteDataSource
import com.lllccww.studyforkproject.data.source.remote.MovieRemoteDataSourceImpl
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val movieModule = module {
    single<NaverMovieRepository> {
        NaverMovieRepositoryImpl(get())
    }

    single<MovieRemoteDataSource> {
        MovieRemoteDataSourceImpl(get())
    }

    single<ApiService> {
        Retrofit.Builder()
            .baseUrl(Constants.API_SERVER_URI)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
package com.lllccww.remote.di

import com.lllccww.data.source.remote.MovieRemoteDataSource
import com.lllccww.remote.Constants
import com.lllccww.remote.ApiService
import com.lllccww.remote.model.MovieRemoteDataSourceImpl
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val remoteDataModule = module {
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
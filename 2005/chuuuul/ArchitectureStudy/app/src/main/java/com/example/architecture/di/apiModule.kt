package com.example.architecture.di

import com.example.architecture.retrofit.MovieSearchService
import com.example.architecture.util.ConstValue
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {

    single<MovieSearchService> {
        Retrofit.Builder()
            .baseUrl(ConstValue.MOVIE_SEARCH_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieSearchService::class.java)
    }

}
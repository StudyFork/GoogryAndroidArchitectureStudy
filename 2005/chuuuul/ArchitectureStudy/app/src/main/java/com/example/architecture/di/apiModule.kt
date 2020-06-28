package com.example.architecture.di

import com.chul.data.retrofit.MovieSearchService
import com.example.architecture.util.ConstValue.MOVIE_SEARCH_API_URL
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {

    single<MovieSearchService> {
        Retrofit.Builder()
            .baseUrl(MOVIE_SEARCH_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieSearchService::class.java)
    }

}
package com.example.data.module

import com.example.data.network.MovieApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single { get<Retrofit>().create(MovieApi::class.java) }
}
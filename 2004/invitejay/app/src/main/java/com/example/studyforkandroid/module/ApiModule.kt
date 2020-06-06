package com.example.studyforkandroid.module

import com.example.studyforkandroid.network.MovieApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single { get<Retrofit>().create(MovieApi::class.java) }
}
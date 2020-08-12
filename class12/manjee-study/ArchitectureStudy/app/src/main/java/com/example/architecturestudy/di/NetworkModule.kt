package com.example.architecturestudy.di

import com.example.architecturestudy.data.source.remote.MovieRemoteService
import org.koin.dsl.module

val networkModule = module {
    single { MovieRemoteService.movieApiService }
}
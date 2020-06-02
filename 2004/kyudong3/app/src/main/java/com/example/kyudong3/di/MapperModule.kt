package com.example.kyudong3.di

import com.example.kyudong3.mapper.MovieLocalMapper
import com.example.kyudong3.mapper.MovieRemoteMapper
import org.koin.dsl.module

val mapperModule = module {
    single { MovieRemoteMapper() }
    single { MovieLocalMapper() }
}
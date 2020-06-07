package com.kyudong.local.di

import com.kyudong.local.mapper.MovieLocalMapper
import org.koin.dsl.module

val localMapperModule = module {
    single { MovieLocalMapper() }
}
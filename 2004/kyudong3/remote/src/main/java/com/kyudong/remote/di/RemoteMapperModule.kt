package com.kyudong.remote.di

import com.kyudong.remote.mapper.MovieRemoteMapper
import org.koin.dsl.module

val remoteMapperModule = module {
    single { MovieRemoteMapper() }
}
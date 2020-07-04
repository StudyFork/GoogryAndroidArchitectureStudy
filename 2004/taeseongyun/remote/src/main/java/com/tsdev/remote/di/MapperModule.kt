package com.tsdev.remote.di

import com.tsdev.data.model.MovieResponse
import com.tsdev.remote.network.mapper.Mapper
import com.tsdev.remote.network.mapper.MapperImpl
import com.tsdev.remote.source.MovieDomainResponse
import org.koin.dsl.module

val remoteMapperModule = module {
    single<Mapper<MovieResponse, MovieDomainResponse>> { MapperImpl() }
}
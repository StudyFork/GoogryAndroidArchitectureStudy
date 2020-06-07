package com.tsdev.remote.di

import com.tsdev.data.source.MovieResponse
import com.tsdev.remote.network.mapper.Mapper
import com.tsdev.remote.network.mapper.MapperImpl
import com.tsdev.remote.source.MovieDomainResponse
import io.reactivex.rxjava3.core.Single
import org.koin.dsl.module

val mapperModule = module {
    single<Mapper<MovieResponse, MovieDomainResponse>> { MapperImpl() }
}
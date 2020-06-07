package com.tsdev.domain.di

import com.tsdev.data.source.Item
import com.tsdev.data.source.MovieResponse
import com.tsdev.domain.mapper.Mapper
import com.tsdev.domain.mapper.MapperImpl
import org.koin.dsl.module

val domainMapperModule = module {
    single<Mapper<MovieResponse, List<Item>>> { MapperImpl() }
}
package com.tsdev.domain.di

import com.tsdev.domain.mapper.Mapper
import com.tsdev.domain.mapper.MapperImpl
import com.tsdev.domain.model.Item
import com.tsdev.domain.model.MovieResponse
import org.koin.dsl.module

val domainMapperModule = module {
    single<Mapper<MovieResponse, List<Item>>> { MapperImpl() }
}
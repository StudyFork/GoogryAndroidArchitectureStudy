package com.tsdev.data.di

import com.tsdev.data.mapper.Mapper
import com.tsdev.data.mapper.MapperImpl
import com.tsdev.data.model.EntryItem
import com.tsdev.domain.model.DomainItem
import org.koin.dsl.module

val mapperModule = module {
    single<Mapper<EntryItem, DomainItem>> { MapperImpl }
}
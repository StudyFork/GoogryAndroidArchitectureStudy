package com.tsdev.remote.di

import com.tsdev.data.model.EntryItem
import com.tsdev.remote.model.RemoteItem
import com.tsdev.remote.network.mapper.Mapper
import com.tsdev.remote.network.mapper.MapperImpl
import org.koin.dsl.module

val remoteMapperModule = module {
    single<Mapper<EntryItem, RemoteItem>> { MapperImpl }
}
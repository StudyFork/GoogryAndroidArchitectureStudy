package com.tsdev.tsandroid.di

import com.tsdev.tsandroid.data.Item
import com.tsdev.tsandroid.util.AbstractMapConverter
import com.tsdev.tsandroid.util.MapConverter
import org.koin.dsl.module

val mapConverterModule = module {
    single<AbstractMapConverter<List<Item>, List<Item>>> { MapConverter() }
}
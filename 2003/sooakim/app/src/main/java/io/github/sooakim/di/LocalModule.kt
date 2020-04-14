package io.github.sooakim.di

import io.github.sooakim.data.local.SADatabase
import io.github.sooakim.data.local.pref.SAPreferencesHelper
import io.github.sooakim.data.local.pref.SAPreferencesHelperImpl
import io.github.sooakim.data.local.source.SAAuthLocalDataSource
import io.github.sooakim.data.local.source.SAAuthLocalDataSourceImpl
import io.github.sooakim.data.local.source.SAMovieLocalDataSource
import io.github.sooakim.data.local.source.SAMovieLocalDataSourceImpl
import org.koin.dsl.module

val localModule = module {
    single { SADatabase.Factory.create(get()) }

    single { get<SADatabase>().movieDao }

    single<SAPreferencesHelper> { SAPreferencesHelperImpl(get()) }

    single<SAAuthLocalDataSource> { SAAuthLocalDataSourceImpl(get()) }

    single<SAMovieLocalDataSource> { SAMovieLocalDataSourceImpl(get(), get()) }
}
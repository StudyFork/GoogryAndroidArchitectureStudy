package io.github.sooakim.local.di

import io.github.sooakim.data.local.SAAuthLocalDataSource
import io.github.sooakim.data.local.SAMovieLocalDataSource
import io.github.sooakim.local.SAAuthLocalDataSourceImpl
import io.github.sooakim.local.SADatabase
import io.github.sooakim.local.SAMovieLocalDataSourceImpl
import io.github.sooakim.local.pref.SAPreferencesHelper
import io.github.sooakim.local.pref.SAPreferencesHelperImpl
import org.koin.dsl.module

val localModule = module {
    single { SADatabase.Factory.create(get()) }

    single { get<SADatabase>().movieDao }

    single<SAPreferencesHelper> { SAPreferencesHelperImpl(get()) }

    single<SAAuthLocalDataSource> { SAAuthLocalDataSourceImpl(get()) }

    single<SAMovieLocalDataSource> { SAMovieLocalDataSourceImpl(get(), get()) }
}
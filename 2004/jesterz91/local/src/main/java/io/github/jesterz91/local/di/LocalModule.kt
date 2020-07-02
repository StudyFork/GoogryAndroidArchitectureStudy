package io.github.jesterz91.local.di

import androidx.room.Room
import io.github.jesterz91.data.source.MovieLocalDataSource
import io.github.jesterz91.local.db.MovieDatabase
import io.github.jesterz91.local.source.MovieLocalDataSourceImpl
import org.koin.dsl.module

val localModule = module {

    single<MovieLocalDataSource> { MovieLocalDataSourceImpl(get()) }

    single { get<MovieDatabase>().movieDao() }

    single { Room.inMemoryDatabaseBuilder(get(), MovieDatabase::class.java).build() }
}
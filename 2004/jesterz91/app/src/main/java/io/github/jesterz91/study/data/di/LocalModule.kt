package io.github.jesterz91.study.data.di

import androidx.room.Room
import io.github.jesterz91.study.data.local.db.MovieDatabase
import io.github.jesterz91.study.data.local.source.MovieLocalDataSource
import io.github.jesterz91.study.data.local.source.MovieLocalDataSourceImpl
import org.koin.dsl.module

val localModule = module {

    single<MovieLocalDataSource> { MovieLocalDataSourceImpl(get()) }

    single { get<MovieDatabase>().movieDao() }

    single { Room.inMemoryDatabaseBuilder(get(), MovieDatabase::class.java).build() }
}
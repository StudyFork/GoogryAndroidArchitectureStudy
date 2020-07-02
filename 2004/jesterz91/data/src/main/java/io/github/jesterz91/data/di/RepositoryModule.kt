package io.github.jesterz91.data.di

import io.github.jesterz91.data.repository.MovieRepositoryImpl
import io.github.jesterz91.domain.repository.MovieRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
}
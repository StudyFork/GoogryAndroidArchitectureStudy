package io.github.sooakim.data.di

import io.github.sooakim.data.SAAuthRepositoryImpl
import io.github.sooakim.data.SAMovieRepositoryImpl
import io.github.sooakim.domain.repository.SAAuthRepository
import io.github.sooakim.domain.repository.SAMovieRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<SAAuthRepository> { SAAuthRepositoryImpl(get(), get()) }

    single<SAMovieRepository> { SAMovieRepositoryImpl(get(), get()) }
}
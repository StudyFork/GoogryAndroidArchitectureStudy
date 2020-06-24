package com.lllccww.studyforkproject.di

import com.lllccww.studyforkproject.data.repository.NaverMovieRepository
import com.lllccww.studyforkproject.data.repository.NaverMovieRepositoryImpl
import com.lllccww.studyforkproject.data.source.remote.MovieRemoteDataSource
import com.lllccww.studyforkproject.data.source.remote.MovieRemoteDataSourceImpl
import org.koin.dsl.module

val movieModule = module {
    single<NaverMovieRepository> {
        NaverMovieRepositoryImpl()
    }

    single<MovieRemoteDataSource> {
        MovieRemoteDataSourceImpl()
    }
}
package com.lllccww.data.di


import com.lllccww.data.repository.NaverMovieRepository
import com.lllccww.data.repository.NaverMovieRepositoryImpl
import org.koin.dsl.module


val repositoryModule = module {
    single<NaverMovieRepository> {
        NaverMovieRepositoryImpl(get())
    }


}
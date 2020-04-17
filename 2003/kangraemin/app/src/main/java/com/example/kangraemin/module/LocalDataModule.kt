package com.example.kangraemin.module

import androidx.room.Room
import com.example.kangraemin.model.AppDatabase
import com.example.kangraemin.model.local.datadao.AuthLocalDataSource
import com.example.kangraemin.model.local.datadao.AuthLocalDataSourceImpl
import com.example.kangraemin.model.local.datadao.LocalMovieDataSource
import com.example.kangraemin.model.local.datadao.LocalMovieDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localDataModule = module {

    // For AppDatabase instance
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "local"
        ).build()
    }

    // For authDao instance
    single { get<AppDatabase>().authDao() }

    // For AuthLocalDataSource instance
    single<AuthLocalDataSource> { AuthLocalDataSourceImpl(get()) }

    // For MovieDao instance
    single { get<AppDatabase>().movieDao() }

    // For MovieRemoteDataSource instance
    single<LocalMovieDataSource> { LocalMovieDataSourceImpl(get()) }

}
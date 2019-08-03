package com.aiden.aiden.architecturepatternstudy.di

import android.app.Application
import com.aiden.aiden.architecturepatternstudy.data.source.UpbitRepository
import com.aiden.aiden.architecturepatternstudy.data.source.local.UpbitDatabase
import com.aiden.aiden.architecturepatternstudy.data.source.local.UpbitLocalDataSource
import com.aiden.aiden.architecturepatternstudy.data.source.remote.UpbitRemoteDataSource
import org.koin.dsl.module

fun getRepositoryModule(application: Application) = module {
    single {
        UpbitRemoteDataSource(get())
    }
    single {
        UpbitDatabase(application)
    }
    single {
        UpbitLocalDataSource(get())
    }
    single {
        UpbitRepository(get(), get())
    }
}

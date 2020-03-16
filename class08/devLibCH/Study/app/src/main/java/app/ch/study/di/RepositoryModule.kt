package app.ch.study.di

import android.content.Context
import app.ch.study.data.common.PREF_NAME
import app.ch.study.data.local.LocalDataManager
import app.ch.study.data.local.source.NaverQueryLocalDataSource
import app.ch.study.data.local.source.NaverQueryLocalDataSourceImpl
import app.ch.study.data.remote.source.NaverQueryRemoteDataSource
import app.ch.study.data.remote.source.NaverQueryRemoteDataSourceImpl
import app.ch.study.data.repository.NaverQueryRepository
import app.ch.study.data.repository.NaverQueryRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModelModule = module {

    single<NaverQueryLocalDataSource> {
        val prefs = androidContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val localDataManager = LocalDataManager.getInstance(prefs)

        NaverQueryLocalDataSourceImpl(localDataManager)
    }

    single<NaverQueryRemoteDataSource> {
        NaverQueryRemoteDataSourceImpl(get())
    }

    single<NaverQueryRepository> {
        NaverQueryRepositoryImpl(get(), get())
    }

}

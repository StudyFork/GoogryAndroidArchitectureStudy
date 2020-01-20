package com.egiwon.local.di

import androidx.room.Room
import com.egiwon.data.NaverLocalDataSource
import com.egiwon.local.ContentDataBase
import com.egiwon.local.source.impl.NaverLocalDataSourceImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localDataSourceModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            ContentDataBase::class.java, ContentDataBase.DB_NAME
        ).build()
    }
    single { get<ContentDataBase>().contentDao() }
    single<NaverLocalDataSource> { NaverLocalDataSourceImpl(get()) }
}
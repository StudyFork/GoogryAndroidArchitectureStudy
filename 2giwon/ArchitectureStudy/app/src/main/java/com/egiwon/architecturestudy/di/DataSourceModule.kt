package com.egiwon.architecturestudy.di

import androidx.room.Room
import com.egiwon.architecturestudy.data.NaverDataRepository
import com.egiwon.architecturestudy.data.NaverDataRepositoryImpl
import com.egiwon.architecturestudy.data.source.NaverDataSource
import com.egiwon.architecturestudy.data.source.local.ContentDataBase
import com.egiwon.architecturestudy.data.source.local.NaverLocalDataSource
import com.egiwon.architecturestudy.data.source.remote.NaverRemoteDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataSourceModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            ContentDataBase::class.java, ContentDataBase.DB_NAME
        ).build()
    }
    single { get<ContentDataBase>().contentDao() }
    single<NaverDataSource.Local> { NaverLocalDataSource(get()) }
    single<NaverDataSource.Remote> { NaverRemoteDataSource(get()) }
    single<NaverDataRepository> { NaverDataRepositoryImpl(get(), get()) }
}

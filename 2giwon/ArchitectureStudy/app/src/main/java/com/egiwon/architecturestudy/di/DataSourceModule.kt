package com.egiwon.architecturestudy.di

import android.content.Context
import androidx.room.Room
import com.egiwon.architecturestudy.data.NaverDataRepository
import com.egiwon.architecturestudy.data.NaverDataRepositoryImpl
import com.egiwon.architecturestudy.data.source.NaverDataSource
import com.egiwon.architecturestudy.data.source.local.ContentDao
import com.egiwon.architecturestudy.data.source.local.ContentDataBase
import com.egiwon.architecturestudy.data.source.local.NaverLocalDataSource
import com.egiwon.architecturestudy.data.source.remote.NaverRemoteDataSource
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataSourceModule = module {
    single { createDatabase(get()) }
    single { createContentDao(get()) }
    single<NaverDataSource.Local> { NaverLocalDataSource(get()) }
    single<NaverDataSource.Remote> { NaverRemoteDataSource(get(named("remote"))) }
    single<NaverDataRepository>(named("repo")) { NaverDataRepositoryImpl(get(), get()) }
}

private fun createDatabase(context: Context): ContentDataBase = Room.databaseBuilder(
    context.applicationContext,
    ContentDataBase::class.java, ContentDataBase.DB_NAME
).build()

private fun createContentDao(contentDataBase: ContentDataBase): ContentDao =
    contentDataBase.contentDao()
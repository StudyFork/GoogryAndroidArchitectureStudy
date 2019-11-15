package com.test.androidarchitecture.di

import com.test.androidarchitecture.data.source.UpbitRemoteDataSource
import com.test.androidarchitecture.data.source.UpbitRemoteDataSourceImpl
import com.test.androidarchitecture.data.source.UpbitRepository
import com.test.androidarchitecture.network.RetrofitService
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Singleton

@Module(includes = [ApplicationModuleBinds::class])
object ApplicationModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RemoteDataSource

    @JvmStatic
    @Singleton
    @RemoteDataSource
    @Provides
    fun provideUpbitRemoteDataSource(retrofitService: RetrofitService): UpbitRemoteDataSource {
        return UpbitRemoteDataSourceImpl(retrofitService)
    }
}

@Module
abstract class ApplicationModuleBinds {

    @Singleton
    @Binds
    abstract fun bindUpbitRepository(upbitRepository: UpbitRepository): UpbitRemoteDataSource
}
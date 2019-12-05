package com.android.studyfork.dagger.module

import com.android.studyfork.repository.UpbitRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun proviedeUpbitRepository(): UpbitRepositoryImpl = UpbitRepositoryImpl()

}
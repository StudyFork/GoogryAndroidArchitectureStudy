package com.android.studyfork.dagger.module

import com.android.studyfork.network.source.UpbitApi
import com.android.studyfork.repository.UpbitRepository
import com.android.studyfork.repository.UpbitRepositoryImpl2
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun proviedeUpbitRepository(): UpbitRepositoryImpl2 = UpbitRepositoryImpl2()

}
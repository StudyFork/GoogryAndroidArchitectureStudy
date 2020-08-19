package com.dwl.study_library.di

import com.dwl.study_library.repository.NaverRepository
import com.dwl.study_library.repository.NaverRepositoryImpl
import com.dwl.study_library.service.BASE_URL
import com.dwl.study_library.service.MovieApi
import com.dwl.study_library.source.NaverRemoteDataSource
import com.dwl.study_library.source.NaverRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object MovieServiceModule {
    @Singleton
    @Provides
    fun provideNaverService(
    ): MovieApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }
}

@Module
@InstallIn(ApplicationComponent::class)
abstract class NaverRemoteDatasoruceModule {

    @Singleton
    @Binds
    abstract fun bindNaverRemoteDatasoruce(
        naverRemoteDataSourceImpl: NaverRemoteDataSourceImpl
    ): NaverRemoteDataSource
}

@Module
@InstallIn(ApplicationComponent::class)
abstract class NaverRepositoryModule {
    @Singleton
    @Binds
    abstract fun bindNaverRepository(
        naverRepositoryImpl: NaverRepositoryImpl
    ): NaverRepository
}
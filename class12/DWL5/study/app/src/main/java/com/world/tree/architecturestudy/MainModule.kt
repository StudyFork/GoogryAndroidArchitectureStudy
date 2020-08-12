package com.world.tree.architecturestudy

import com.world.tree.architecturestudy.model.BASE_URL
import com.world.tree.architecturestudy.model.MovieApi
import com.world.tree.architecturestudy.model.repository.remote.NaverRepository
import com.world.tree.architecturestudy.model.repository.remote.NaverRepositoryImpl
import com.world.tree.architecturestudy.model.source.remote.NaverRemoteDataSource
import com.world.tree.architecturestudy.model.source.remote.NaverRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
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
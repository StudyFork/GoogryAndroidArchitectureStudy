package com.wybh.androidarchitecturestudy.model.remote

import com.wybh.androidarchitecturestudy.data.ResponseCinemaData
import com.wybh.androidarchitecturestudy.retrofit.RetrofitCreator
import com.wybh.androidarchitecturestudy.retrofit.RetrofitImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

class NaverRemoteDataSourceImpl @Inject constructor(
    private val retrofitCreator: RetrofitCreator
) : NaverRemoteDataSource {

    override fun searchCinema(query: String): Single<ResponseCinemaData> {
        return retrofitCreator.create(RetrofitImpl::class.java)
            .getCinemaData(query)
    }
}

@Module
@InstallIn(ApplicationComponent::class)
abstract class NaverRemoteDataSourceModule {
    @Binds
    @Singleton
    abstract fun bindNaverRemoteDataSource(remoteDataSourceImpl: NaverRemoteDataSourceImpl): NaverRemoteDataSource
}
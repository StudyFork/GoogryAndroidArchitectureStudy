package com.example.architecturestudy

import com.example.architecturestudy.data.repository.NaverSearchRepository
import com.example.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.example.architecturestudy.data.source.remote.NaverSearchRemoteDataSourceImpl
import com.example.architecturestudy.network.ApiClient

object Injection {
    fun provideNaverSearchRepository() : NaverSearchRepository {
        return NaverSearchRepositoryImpl(
            NaverSearchRemoteDataSourceImpl(
                ApiClient.api
            )
        )
    }
}
package com.example.architecturestudy

import android.content.Context
import com.example.architecturestudy.data.local.NaverDataBase
import com.example.architecturestudy.data.repository.NaverSearchRepository
import com.example.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.example.architecturestudy.data.source.local.NaverSearchLocalDataSourceImpl
import com.example.architecturestudy.data.source.remote.NaverSearchRemoteDataSourceImpl
import com.example.architecturestudy.network.ApiClient
import com.example.architecturestudy.util.AppExecutor
import java.util.concurrent.Executor

object Injection {

    fun provideNaverSearchRepository(
        context: Context
    ) : NaverSearchRepository {
        return NaverSearchRepositoryImpl(
            NaverSearchRemoteDataSourceImpl(
                ApiClient.api
            ),
            NaverSearchLocalDataSourceImpl(
                NaverDataBase.getInstance(context)
            )
        )
    }
}
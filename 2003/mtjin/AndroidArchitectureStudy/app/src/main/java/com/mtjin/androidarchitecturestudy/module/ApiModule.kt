package com.mtjin.androidarchitecturestudy.module

import com.mtjin.androidarchitecturestudy.api.ApiClient
import com.mtjin.androidarchitecturestudy.api.ApiInterface
import org.koin.core.module.Module
import org.koin.dsl.module

val apiModule: Module = module {
    single<ApiInterface> { ApiClient.getApiClient().create(ApiInterface::class.java) }

}
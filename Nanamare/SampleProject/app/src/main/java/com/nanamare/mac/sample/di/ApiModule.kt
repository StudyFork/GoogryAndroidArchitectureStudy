package com.nanamare.mac.sample.di

import com.nanamare.mac.sample.api.upbit.UpBitService
import com.nanamare.mac.sample.api.upbit.UpBitServiceManager
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

val apiManagerModule: Module = module {

    single {
        UpBitServiceManager((get() as Retrofit).create(UpBitService::class.java))
    }

}
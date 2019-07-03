package com.namjackson.archstudy.di

import com.namjackson.archstudy.base.UPBIT_API
import com.namjackson.archstudy.data.source.remote.upbit.UpbitApi
import com.namjackson.archstudy.network.RetrofitCreator
import org.koin.dsl.module

val networkModule = module {
    single { RetrofitCreator.createRetrofit(UPBIT_API, UpbitApi::class.java) }
}
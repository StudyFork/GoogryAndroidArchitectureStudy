package com.namjackson.archstudy.network

import com.namjackson.archstudy.base.UPBIT_API
import com.namjackson.archstudy.network.api.UpbitApi

object UpbitService {
    val upbitApi: UpbitApi by lazy {
        RetrofitCreator.createRetrofit(UPBIT_API, UpbitApi::class.java)
    }
}
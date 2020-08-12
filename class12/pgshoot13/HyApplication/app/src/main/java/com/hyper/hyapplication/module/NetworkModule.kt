package com.hyper.hyapplication.module

import com.hyper.hyapplication.BASE_URL
import com.hyper.hyapplication.NaverAPI
import org.koin.dsl.module
import retrofit2.Retrofit

val remoteModule = module {

    single { get<Retrofit>().create(NaverAPI::class.java) }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(get())
            .addCallAdapterFactory(get())
            .build()
    }
}
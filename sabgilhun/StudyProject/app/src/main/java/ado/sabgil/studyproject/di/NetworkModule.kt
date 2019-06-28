package ado.sabgil.studyproject.di

import ado.sabgil.studyproject.data.remote.upbit.UpbitApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val baseURL = "https://api.upbit.com/v1/"

val networkModule = module {

    single {
        Retrofit.Builder().baseUrl(baseURL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UpbitApi::class.java)
    }
}
package app.ch.study.di

import app.ch.study.data.remote.api.WebApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    //api
    single {
        provideApi(get())
    }
}

fun provideApi(retrofit: Retrofit) : WebApi {
    return retrofit.create(WebApi::class.java)
}
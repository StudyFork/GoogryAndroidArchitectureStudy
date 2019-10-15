package study.architecture.myarchitecture.di

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import study.architecture.myarchitecture.data.source.remote.UpbitApi
import study.architecture.myarchitecture.data.source.remote.UpbitRemoteDataSource
import study.architecture.myarchitecture.data.source.remote.UpbitRemoteLocalDataSourceImpl

const val HOST_URL = "https://api.upbit.com/"

val remoteModule = module {

    single<UpbitRemoteDataSource> {
        UpbitRemoteLocalDataSourceImpl(get())
    }

    single {
        Retrofit.Builder()
            .baseUrl(HOST_URL)
            .client(get())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UpbitApi::class.java)
    }
}
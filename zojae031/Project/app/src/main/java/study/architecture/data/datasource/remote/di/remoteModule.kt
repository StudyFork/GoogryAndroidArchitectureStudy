package study.architecture.data.datasource.remote.di

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import study.architecture.data.datasource.remote.RemoteDataSource
import study.architecture.data.datasource.remote.RemoteDataSourceImpl
import study.architecture.data.datasource.remote.UpbitApi

val remoteModule = module {
    single<RemoteDataSource> { RemoteDataSourceImpl.getInstance(get()) }
    single<UpbitApi> {
        Retrofit
            .Builder()
            .baseUrl(RemoteDataSourceImpl.url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(
                UpbitApi::
                class.java
            )
    }
}
package study.architecture.data.datasource.remote.di

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import study.architecture.data.datasource.remote.RemoteDataSource
import study.architecture.data.datasource.remote.RemoteDataSourceImpl
import study.architecture.data.datasource.remote.UpbitApi


val remoteModule = module {
    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }

    factory<GsonConverterFactory> { GsonConverterFactory.create() }
    factory<RxJava2CallAdapterFactory> { RxJava2CallAdapterFactory.create() }
    factory<UpbitApi> { upbitApi(get()) }

    single<Retrofit> {
        Retrofit
            .Builder()
            .baseUrl(upbitUrl)
            .addConverterFactory(get())
            .addCallAdapterFactory(get())
            .build()
    }

}

fun upbitApi(retrofit: Retrofit): UpbitApi =
    retrofit.create(UpbitApi::class.java)

const val upbitUrl = "https://api.upbit.com/v1/"
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
    single { get<Retrofit>().create(UpbitApi::class.java) }
    single<Retrofit> {
        Retrofit
            .Builder()
            .baseUrl(upbitUrl)
            .addConverterFactory(get<GsonConverterFactory>())
            .addCallAdapterFactory(get<RxJava2CallAdapterFactory>())
            .build()
    }

}


const val upbitUrl = "https://api.upbit.com/v1/"
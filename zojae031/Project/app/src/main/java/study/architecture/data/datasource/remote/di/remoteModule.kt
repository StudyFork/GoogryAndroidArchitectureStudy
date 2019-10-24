package study.architecture.data.datasource.remote.di

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import study.architecture.data.datasource.remote.RemoteDataSource
import study.architecture.data.datasource.remote.RemoteDataSourceImpl

val remoteModule = module {
    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }
    single { GsonConverterFactory.create() }
    single { RxJava2CallAdapterFactory.create() }
    single<Retrofit> {
        Retrofit
            .Builder()
            .baseUrl(RemoteDataSourceImpl.url)
            .addConverterFactory(get())
            .addCallAdapterFactory(get())
            .build()
    }

}
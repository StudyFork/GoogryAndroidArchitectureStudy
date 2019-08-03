package com.architecturestudy.di

import androidx.room.Room
import com.architecturestudy.BuildConfig
import com.architecturestudy.data.source.UpbitRepository
import com.architecturestudy.data.source.local.UpbitDatabase
import com.architecturestudy.data.source.local.UpbitLocalDataSource
import com.architecturestudy.data.source.remote.UpbitRemoteDataSource
import com.architecturestudy.data.source.remote.UpbitRemoteService
import com.architecturestudy.market.UpbitViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModules = module {
    viewModel { UpbitViewModel(get()) }
}

val dataSourceModules = module {
    single { UpbitRepository(get(), get()) }
    single { UpbitLocalDataSource(get()) }
    single { UpbitRemoteDataSource(get()) }
}

fun getRemoteServiceModules(url: String) = module {
    single {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }
    single { GsonConverterFactory.create() }
    single { RxJava2CallAdapterFactory.create() }
    single {
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(get<GsonConverterFactory>())
            .addCallAdapterFactory(get<RxJava2CallAdapterFactory>())
            .client(get<OkHttpClient>())
            .build()
    }
    single { get<Retrofit>().create(UpbitRemoteService::class.java) }

}

fun getLocalServiceModules() = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            UpbitDatabase::class.java,
            "UpbitTicker.db"
        ).build()
    }
    single { get<UpbitDatabase>().upbitTickerDao() }
}
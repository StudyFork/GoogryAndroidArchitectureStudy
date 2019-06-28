package my.gong.studygong.di

import my.gong.studygong.data.network.RetrofitProvider
import org.koin.dsl.module

val networkModule = module {
    single { RetrofitProvider() }
}
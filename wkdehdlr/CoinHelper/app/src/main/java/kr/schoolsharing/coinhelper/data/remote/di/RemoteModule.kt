package kr.schoolsharing.coinhelper.data.remote.di

import kr.schoolsharing.coinhelper.data.remote.UpbitRemoteDataSource
import kr.schoolsharing.coinhelper.network.UpbitService
import org.koin.dsl.module
import retrofit2.Retrofit

val remoteModule = module {
    single<UpbitService> { get<Retrofit>().create(UpbitService::class.java) }
    single { UpbitRemoteDataSource(get()) }
}
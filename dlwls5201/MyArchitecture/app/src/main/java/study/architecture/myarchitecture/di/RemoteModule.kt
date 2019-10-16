package study.architecture.myarchitecture.di

import org.koin.dsl.module
import retrofit2.Retrofit
import study.architecture.myarchitecture.data.source.remote.UpbitApi
import study.architecture.myarchitecture.data.source.remote.UpbitRemoteDataSource
import study.architecture.myarchitecture.data.source.remote.UpbitRemoteLocalDataSourceImpl

val remoteModule = module {

    single<UpbitRemoteDataSource> {
        UpbitRemoteLocalDataSourceImpl(get())
    }

    single {
        get<Retrofit>().create(UpbitApi::class.java)
    }
}
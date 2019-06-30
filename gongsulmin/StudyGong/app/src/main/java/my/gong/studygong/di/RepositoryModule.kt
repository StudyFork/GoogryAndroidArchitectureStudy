package my.gong.studygong.di

import my.gong.studygong.data.network.UpbitApi
import my.gong.studygong.data.source.upbit.UpbitDataSource
import my.gong.studygong.data.source.upbit.UpbitRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<UpbitDataSource> { UpbitRepository(get() as UpbitApi) }
}

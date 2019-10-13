package kr.schoolsharing.coinhelper.data.di

import kr.schoolsharing.coinhelper.data.Repository
import org.koin.dsl.module

val repositoryModule = module {
    single { Repository(get()) }
}
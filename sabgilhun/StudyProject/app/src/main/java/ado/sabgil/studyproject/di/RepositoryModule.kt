package ado.sabgil.studyproject.di

import ado.sabgil.studyproject.data.CoinRepository
import ado.sabgil.studyproject.data.CoinRepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {

    single {
        CoinRepositoryImpl(get(named(UPBIT_DATA_SOURCE))) as CoinRepository
    }
}
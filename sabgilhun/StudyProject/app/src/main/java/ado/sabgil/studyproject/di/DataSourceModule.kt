package ado.sabgil.studyproject.di

import ado.sabgil.studyproject.data.remote.CoinDataSource
import ado.sabgil.studyproject.data.remote.upbit.UpbitCoinDataSourceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val UPBIT_DATA_SOURCE = "UPBIT_DATA_SOURCE"

val dataSourceModule = module {

    single(named(UPBIT_DATA_SOURCE)) {
        UpbitCoinDataSourceImpl(get()) as CoinDataSource
    }
}
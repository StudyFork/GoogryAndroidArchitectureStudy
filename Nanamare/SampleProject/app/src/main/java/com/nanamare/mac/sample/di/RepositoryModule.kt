package com.nanamare.mac.sample.di

import com.nanamare.mac.sample.data.coin.CoinRemoteDataSource
import com.nanamare.mac.sample.data.coin.CoinRepository
import com.nanamare.mac.sample.data.market.MarketRemoteDataSource
import com.nanamare.mac.sample.data.market.MarketRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { CoinRepository(get())}
    single { MarketRepository(get())}
}

val dataSourceModule = module {
    single { CoinRemoteDataSource(get())  }
    single { MarketRemoteDataSource(get())  }
}
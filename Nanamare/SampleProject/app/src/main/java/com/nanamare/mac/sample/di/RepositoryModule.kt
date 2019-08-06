package com.nanamare.mac.sample.di

import com.nanamare.mac.sample.data.coin.CoinRemoteDataSource
import com.nanamare.mac.sample.data.coin.CoinRepository
import com.nanamare.mac.sample.data.coin.CoinSource
import com.nanamare.mac.sample.data.market.MarketRemoteDataSource
import com.nanamare.mac.sample.data.market.MarketRepository
import com.nanamare.mac.sample.data.market.MarketSource
import org.koin.dsl.module

val repositoryModule = module {
    single { CoinRepository(get()) }
    single { MarketRepository(get()) }
}

val dataSourceModule = module {
    single { CoinRemoteDataSource(get()) as CoinSource }
    single { MarketRemoteDataSource(get()) as MarketSource}
}
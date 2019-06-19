package com.nanamare.mac.sample.data

import com.nanamare.mac.sample.api.upbit.TickerModel

object CoinRepository : CoinSource {

    override fun getCoins(ticketList: MutableList<String>, success: (List<TickerModel>) -> Unit, failed: () -> Unit) {
        CoinRemoteDataSource.getCoins(ticketList, success, failed)
    }

    override fun close() {
        CoinRemoteDataSource.close()
    }

}
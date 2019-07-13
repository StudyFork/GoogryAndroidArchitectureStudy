package com.nanamare.mac.sample.data.coin

import com.nanamare.mac.sample.api.upbit.CoinModel

object CoinRepository : CoinSource {

    override fun getCoins(ticketList: MutableList<String>, success: (List<CoinModel>) -> Unit, failed: () -> Unit) {
        CoinRemoteDataSource.getCoins(ticketList, success, failed)
    }

    override fun close() {
        CoinRemoteDataSource.close()
    }

}
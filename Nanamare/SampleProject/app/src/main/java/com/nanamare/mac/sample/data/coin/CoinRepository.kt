package com.nanamare.mac.sample.data.coin

import com.nanamare.mac.sample.api.upbit.CoinModel

class CoinRepository(private val coinSource: CoinSource) : CoinSource {

    override fun getCoins(ticketList: MutableList<String>, success: (List<CoinModel>) -> Unit, failed: () -> Unit) {
        coinSource.getCoins(ticketList, success, failed)
    }

    override fun close() {
        coinSource.close()
    }

}
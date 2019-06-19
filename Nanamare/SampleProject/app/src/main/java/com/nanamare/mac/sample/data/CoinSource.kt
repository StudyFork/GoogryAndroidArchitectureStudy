package com.nanamare.mac.sample.data

import com.nanamare.mac.sample.api.upbit.TickerModel

interface CoinSource : CommonSource {
    fun getCoins(ticketList: MutableList<String>, success: (List<TickerModel>) -> Unit, failed: () -> Unit)
}
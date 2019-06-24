package com.nanamare.mac.sample.data.coin

import com.nanamare.mac.sample.api.upbit.TickerModel
import com.nanamare.mac.sample.data.CommonSource

interface CoinSource : CommonSource {
    fun getCoins(ticketList: MutableList<String>, success: (List<TickerModel>) -> Unit, failed: () -> Unit)
}
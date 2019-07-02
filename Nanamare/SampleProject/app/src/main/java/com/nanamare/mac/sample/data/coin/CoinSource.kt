package com.nanamare.mac.sample.data.coin

import com.nanamare.mac.sample.api.upbit.CoinModel
import com.nanamare.mac.sample.base.BaseSource

interface CoinSource : BaseSource {
    fun getCoins(ticketList: MutableList<String>, success: (List<CoinModel>) -> Unit, failed: () -> Unit)
}
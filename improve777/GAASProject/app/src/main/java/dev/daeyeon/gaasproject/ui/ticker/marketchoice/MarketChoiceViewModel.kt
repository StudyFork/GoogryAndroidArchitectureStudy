package dev.daeyeon.gaasproject.ui.ticker.marketchoice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.daeyeon.gaasproject.base.BaseViewModel
import dev.daeyeon.gaasproject.data.source.UpbitDataSource

class MarketChoiceViewModel(
    var markets: String = UpbitDataSource.ALL_MARKET
) : BaseViewModel() {

    var chooseMarket: String = UpbitDataSource.ALL_MARKET

    /**
     * 마켓 어레이
     */
    private val _marketList = MutableLiveData<List<String>>(initMarketList(markets))
    val marketList: LiveData<List<String>>
        get() = _marketList

    private fun initMarketList(markets: String): List<String> {
        val list = arrayListOf(UpbitDataSource.ALL_CURRENCY)

        if (markets.isNotEmpty()) {
            list.addAll(markets.split(",")
                .map { market -> market.substringBefore("-") }
                .distinct()
            )
        }

        return list
    }
}
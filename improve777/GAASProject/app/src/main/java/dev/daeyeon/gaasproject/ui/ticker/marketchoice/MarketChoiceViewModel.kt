package dev.daeyeon.gaasproject.ui.ticker.marketchoice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.daeyeon.gaasproject.base.BaseViewModel
import dev.daeyeon.gaasproject.data.source.UpbitDataSource

class MarketChoiceViewModel : BaseViewModel() {

    var selectedMarket: String = ""

    /**
     * 마켓 어레이
     */
    private val _marketList = MutableLiveData<List<String>>(emptyList())
    val marketList: LiveData<List<String>>
        get() = _marketList

    fun setMarketList(markets: String) {
        val list = arrayListOf(UpbitDataSource.ALL_CURRENCY)

        if (markets.isNotEmpty()) {
            list.addAll(markets.split(",")
                .map { market -> market.substringBefore("-") }
                .distinct()
            )
        }

        _marketList.value = list
    }
}
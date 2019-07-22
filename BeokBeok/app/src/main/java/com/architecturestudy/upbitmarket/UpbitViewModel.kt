package com.architecturestudy.upbitmarket

import androidx.lifecycle.MutableLiveData
import com.architecturestudy.base.BaseViewModel
import com.architecturestudy.data.common.MarketTypes
import com.architecturestudy.data.upbit.source.UpbitRepository
import com.architecturestudy.util.NumberFormatter
import com.architecturestudy.util.RxEventBus

class UpbitViewModel(
    private val upBitRepository: UpbitRepository
) : BaseViewModel() {

    val marketPriceList = MutableLiveData<List<Map<String, String>>>()
    val errMsg = MutableLiveData<Throwable>()
    val currentTabPosition = MutableLiveData<Int>().apply { value = 0 }

    fun showMarketPrice(prefix: String) {
        upBitRepository.getMarketPrice(
            prefix,
            onSuccess = {
                for (i in 0 until it.size) {
                    upBitRepository.saveTicker(it[i])
                }
                marketPriceList.value = NumberFormatter.convertTo(it)
            },
            onFail = {
                errMsg.value = it
            }
        )
    }

    fun sort(sortType: String) {
        upBitRepository.sort(
            MarketTypes.values()[currentTabPosition.value ?: 0].name,
            sortType,
            onSuccess = {
                RxEventBus.sendEvent(NumberFormatter.convertTo(it))
            },
            onFail = {
                RxEventBus.sendEvent(it)
            }
        )
    }
}
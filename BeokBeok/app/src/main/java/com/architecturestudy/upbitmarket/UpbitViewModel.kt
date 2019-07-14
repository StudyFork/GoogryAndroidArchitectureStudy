package com.architecturestudy.upbitmarket

import androidx.lifecycle.MutableLiveData
import com.architecturestudy.base.BaseViewModel
import com.architecturestudy.data.common.MarketTypes
import com.architecturestudy.data.upbit.UpbitRepository
import com.architecturestudy.util.NumberFormatter

class UpbitViewModel(
    private val upBitRepository: UpbitRepository
) : BaseViewModel() {

    val marketPriceList = MutableLiveData<List<Map<String, String>>>()
    val errMsg = MutableLiveData<Throwable>()

    init {
        upBitRepository.getMarketPrice(
            MarketTypes.KRW.name,
            onSuccess = {
                marketPriceList.postValue(NumberFormatter.convertTo(it))
            },
            onFail = {
                errMsg.value = it
            }
        )
    }

    fun showMarketPrice(market: String) {
        upBitRepository.getMarketPrice(
            market,
            onSuccess = {
                marketPriceList.postValue(NumberFormatter.convertTo(it))
            },
            onFail = {
                errMsg.value = it
            }
        )
    }
}
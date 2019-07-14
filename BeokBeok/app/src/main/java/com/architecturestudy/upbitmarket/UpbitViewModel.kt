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
    val selectedText = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()

    init {
        selectedText.value = MarketTypes.KRW.name
        isLoading.value = true
        upBitRepository.getMarketPrice(
            MarketTypes.KRW.name,
            onSuccess = {
                marketPriceList.value = NumberFormatter.convertTo(it)
                isLoading.value = false
            },
            onFail = {
                errMsg.value = it
                isLoading.value = false
            }
        )
    }

    fun showMarketPrice(market: String) {
        selectedText.value = market
        isLoading.value = true
        upBitRepository.getMarketPrice(
            market,
            onSuccess = {
                marketPriceList.value = NumberFormatter.convertTo(it)
                isLoading.value = false
            },
            onFail = {
                errMsg.value = it
                isLoading.value = false
            }
        )
    }
}
package com.architecturestudy.upbitmarket

import androidx.databinding.ObservableField
import com.architecturestudy.base.BaseViewModel
import com.architecturestudy.data.common.MarketTypes
import com.architecturestudy.data.upbit.UpbitRepository
import com.architecturestudy.util.NumberFormatter

class UpbitViewModel(
    private val upBitRepository: UpbitRepository
) : BaseViewModel() {

    val marketPriceList = ObservableField<List<Map<String, String>>>()
    val errMsg = ObservableField<Throwable>()
    val selectedText = ObservableField<String>()
    val isLoading = ObservableField<Boolean>()

    init {
        selectedText.set(MarketTypes.KRW.name)
        isLoading.set(true)
        upBitRepository.getMarketPrice(
            MarketTypes.KRW.name,
            onSuccess = {
                marketPriceList.set(NumberFormatter.convertTo(it))
                isLoading.set(false)
            },
            onFail = {
                errMsg.set(it)
                isLoading.set(false)
            }
        )
    }

    fun showMarketPrice(market: String) {
        selectedText.set(market)
        isLoading.set(true)
        upBitRepository.getMarketPrice(
            market,
            onSuccess = {
                marketPriceList.set(NumberFormatter.convertTo(it))
                isLoading.set(false)
            },
            onFail = {
                errMsg.set(it)
                isLoading.set(false)
            }
        )
    }
}
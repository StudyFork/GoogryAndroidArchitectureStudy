package com.architecturestudy.upbitmarket

import androidx.lifecycle.MutableLiveData
import com.architecturestudy.base.BaseViewModel
import com.architecturestudy.data.common.MarketTypes
import com.architecturestudy.data.upbit.source.UpbitRepository
import com.architecturestudy.util.NumberFormatter

class UpbitViewModel(
    private val upBitRepository: UpbitRepository
) : BaseViewModel() {

    val marketPriceList = MutableLiveData<List<Map<String, String>>>()
    val errMsg = MutableLiveData<Throwable>()
    val currentTabPosition = MutableLiveData<Int>().apply { value = 0 }

    fun showMarketPrice(market: String) {
        upBitRepository.getMarketPrice(
            market,
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
                marketPriceList.value = NumberFormatter.convertTo(it)
            },
            onFail = {
                errMsg.value = it
            }
        )
    }

    companion object {
        private var instance: UpbitViewModel? = null

        operator fun invoke(
            upBitRepository: UpbitRepository
        ): UpbitViewModel = instance ?: UpbitViewModel(upBitRepository)
            .apply { instance = this }
    }
}
package com.architecturestudy.upbitmarket

import androidx.lifecycle.MutableLiveData
import com.architecturestudy.base.BaseViewModel
import com.architecturestudy.data.upbit.source.UpbitRepository
import com.architecturestudy.util.NumberFormatter
import com.architecturestudy.util.RxEventBus

class UpbitViewModel(
    private val upBitRepository: UpbitRepository
) : BaseViewModel() {

    val marketPriceList = MutableLiveData<List<Map<String, String>>>()
    val errMsg = MutableLiveData<Throwable>()
    val isSortByDESC = MutableLiveData<Boolean>()
    val selectedSortTypeList = MutableLiveData<List<Boolean>>()
        .apply { listOf(false, false, false, false) }
    private var isDESC: Boolean = false

    fun showMarketPrice(prefix: String) {
        addDisposable(
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
        )
    }

    fun sort(sortType: String) {
        setSelectedTypeList(sortType)
        isSortByDESC.value = isDESC
        addDisposable(
            upBitRepository.sort(
                sortType,
                isDESC,
                onSuccess = {
                    RxEventBus.sendEvent(NumberFormatter.convertTo(it))
                    isDESC = !isDESC
                },
                onFail = {
                    RxEventBus.sendEvent(it)
                }
            )
        )
    }

    private fun setSelectedTypeList(sortType: String) {
        when (sortType) {
            "market" -> selectedSortTypeList.value = listOf(true, false, false, false)
            "trade_price" -> selectedSortTypeList.value = listOf(false, true, false, false)
            "signed_change_rate" -> selectedSortTypeList.value = listOf(false, false, true, false)
            "acc_trade_price_24h" -> selectedSortTypeList.value = listOf(false, false, false, true)
        }
    }
}
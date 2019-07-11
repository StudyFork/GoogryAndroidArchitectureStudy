package com.architecturestudy.upbitmarket

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.databinding.ObservableField
import com.architecturestudy.base.BaseViewModel
import com.architecturestudy.data.upbit.UpbitRepository
import com.architecturestudy.util.NumberFormatter

class UpbitViewModel(
    private val upBitRepository: UpbitRepository
) : BaseViewModel() {

    val marketPriceList = ObservableField<List<Map<String, String>>>()
    val errMsg = ObservableField<Throwable>()
    val isMarketTypeClicked = ObservableField<Boolean>()
    val isLoading = ObservableField<Boolean>()

    init {
        upBitRepository.getMarketPrice(
            "KRW",
            onSuccess = {
                marketPriceList.set(NumberFormatter.convertTo(it))
                isLoading.set(false)
            },
            onFail = {
                errMsg.set(it)
                isLoading.set(false)
            }
        )
        isLoading.set(true)
    }

    fun showMarketPrice(v: View) {
        isMarketTypeClicked.set(false)
        if (v is TextView) {
            isLoading.set(true)
            upBitRepository.getMarketPrice(
                v.text.toString(),
                onSuccess = {
                    marketPriceList.set(NumberFormatter.convertTo(it))
                    isLoading.set(false)
                },
                onFail = {
                    errMsg.set(it)
                    isLoading.set(false)
                }
            )
            v.setTextColor(Color.BLUE)
            isMarketTypeClicked.set(true)
        }
    }
}
package com.architecturestudy.upbitmarket

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.databinding.ObservableField
import com.architecturestudy.base.BaseViewModel
import com.architecturestudy.data.upbit.UpbitRepository
import com.architecturestudy.data.upbit.UpbitTicker
import com.architecturestudy.data.upbit.source.UpbitDataSource
import com.architecturestudy.util.NumberFormatter

class UpbitViewModel(
    private val upBitRepository: UpbitRepository
) : UpbitDataSource.GetTickerCallback,
    BaseViewModel() { // TODO callback 제거하기

    var marketPriceList = ObservableField<List<Map<String, String>>>()
    var errMsg = ObservableField<Throwable>()
    var isMarketTypeClicked = ObservableField<Boolean>()

    init {
        upBitRepository.getMarketPrice("KRW", this)
    }

    override fun onTickerLoaded(marketPrice: List<UpbitTicker>) {
        marketPriceList.set(NumberFormatter.convertTo(marketPrice))
    }

    override fun onDataNotAvailable(t: Throwable) {
        errMsg.set(t)
    }

    fun showMarketPrice(v: View) {
        isMarketTypeClicked.set(false)
        if (v is TextView) {
            upBitRepository.getMarketPrice(v.text.toString(), this)
            v.setTextColor(Color.BLUE)
            isMarketTypeClicked.set(true)
        }
    }
}
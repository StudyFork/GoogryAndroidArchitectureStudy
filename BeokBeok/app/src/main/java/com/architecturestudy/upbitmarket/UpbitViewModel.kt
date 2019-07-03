package com.architecturestudy.upbitmarket

import android.view.View
import android.widget.TextView
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import com.architecturestudy.data.upbit.UpbitRepository
import com.architecturestudy.data.upbit.UpbitTicker
import com.architecturestudy.data.upbit.source.UpbitDataSource
import com.architecturestudy.util.NumberFormatter

class UpbitViewModel(
    private val upBitRepository: UpbitRepository
) : UpbitDataSource.GetTickerCallback, BaseObservable() {

    var marketPriceList = ObservableField<List<Map<String, String>>>()
    var errMsg = ObservableField<Throwable>()

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
        // TODO 클릭한 뷰의 글자색만 변경하기
        upBitRepository.getMarketPrice(
            (v as TextView).text.toString(),
            this
        )
    }
}
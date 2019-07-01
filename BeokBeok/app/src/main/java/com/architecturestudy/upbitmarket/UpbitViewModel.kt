package com.architecturestudy.upbitmarket

import android.view.View
import android.widget.TextView
import com.architecturestudy.data.upbit.UpbitRepository
import com.architecturestudy.data.upbit.UpbitTicker
import com.architecturestudy.data.upbit.source.UpbitDataSource
import com.architecturestudy.util.NumberFormatter
import kotlinx.android.synthetic.main.fragment_upbit.*

class UpbitViewModel(
    private val view: UpbitFragment,
    private val upBitRepository: UpbitRepository
) : UpbitDataSource.GetTickerCallback {

    init {
        showMarketPrice(view.tv_market_krw)
    }

    override fun onTickerLoaded(marketPrice: List<UpbitTicker>) {
        view.updateMarketPrice(NumberFormatter.convertTo(marketPrice))
    }

    override fun onDataNotAvailable(t: Throwable) {
        view.showToast(t.message)
    }

    fun showMarketPrice(v: View) {
        view.setViewColor(v)
        upBitRepository.getMarketPrice(
            (v as TextView).text.toString(),
            this
        )
    }
}
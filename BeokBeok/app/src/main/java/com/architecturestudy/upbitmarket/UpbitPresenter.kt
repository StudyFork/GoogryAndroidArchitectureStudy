package com.architecturestudy.upbitmarket

import com.architecturestudy.data.upbit.UpbitRepository
import com.architecturestudy.data.upbit.UpbitTicker
import com.architecturestudy.data.upbit.source.UpbitDataSource
import com.architecturestudy.util.NumberFormatter

class UpbitPresenter(
    private val upbitView: UpbitContract.View,
    private val upBitRepository: UpbitRepository
) : UpbitContract.Presenter, UpbitDataSource.GetTickerCallback {

    override fun start() {
        showMarketPrice("KRW")
    }

    override fun onTickerLoaded(marketPrice: List<UpbitTicker>) {
        upbitView.updateMarketPrice(
            NumberFormatter.convertTo(marketPrice)
        )
    }

    override fun onDataNotAvailable(t: Throwable) {
        upbitView.showErrMsg(t)
    }

    override fun showMarketPrice(prefix: String) {
        upBitRepository.getMarketPrice(prefix, this)
    }
}
package com.architecturestudy.upbitmarket

import com.architecturestudy.data.UpbitRepository
import com.architecturestudy.data.UpbitTicker
import com.architecturestudy.data.source.UpbitDataSource

class UpbitPresenter(
    private val upbitView: UpbitContract.View,
    private val upBitRepository: UpbitRepository
) : UpbitContract.Presenter, UpbitDataSource.GetTickerCallback {

    override fun start() {
        showMarketPrice("KRW")
    }

    override fun onTickerLoaded(marketPrice: List<UpbitTicker>) {
        upbitView.updateMarketPrice(marketPrice)
    }

    override fun onDataNotAvailable(t: Throwable) {
        upbitView.showErrMsg(t)
    }

    override fun showMarketPrice(prefix: String) {
        upBitRepository.getMarketPrice(prefix, this)
    }
}
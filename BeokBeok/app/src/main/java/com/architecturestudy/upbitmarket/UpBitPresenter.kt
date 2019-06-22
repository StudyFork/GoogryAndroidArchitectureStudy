package com.architecturestudy.upbitmarket

import com.architecturestudy.data.UpbitRepository
import com.architecturestudy.data.UpbitTicker
import com.architecturestudy.data.source.UpbitDataSource

class UpBitPresenter(
    private val upBitRepository: UpbitRepository,
    private val upBitView: UpBitContract.View
) : UpBitContract.Presenter, UpbitDataSource.GetTickerCallback {

    init {
        upBitView.presenter = this
    }

    override fun start() {
        showMarketPrice("KRW")
    }

    override fun onTickerLoaded(marketPrice: List<UpbitTicker>) {
        upBitView.updateMarketPrice(marketPrice)
    }

    override fun onDataNotAvailable(err: String?) {
        err?.let { upBitView.showErrMsg(it) }
    }

    override fun showMarketPrice(prefix: String) {
        upBitRepository.getMarketPrice(prefix, this)
    }
}
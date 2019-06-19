package com.architecturestudy.upbitmarket

import com.architecturestudy.data.UpBitRepository
import com.architecturestudy.data.UpBitTicker
import com.architecturestudy.data.source.UpBitDataSource

class UpBitPresenter(
    private val upBitRepository: UpBitRepository,
    private val upBitView: UpBitContract.View
) : UpBitContract.Presenter, UpBitDataSource.GetTickerCallback {

    init {
        upBitView.presenter = this
    }

    override fun start() {
        showMarketPrice("KRW")
    }

    override fun onTickerLoaded(marketPrice: List<UpBitTicker>) {
        upBitView.updateMarketPrice(marketPrice)
    }

    override fun onDataNotAvailable(err: String?) {
        err?.let { upBitView.showErrMsg(it) }
    }

    override fun showMarketPrice(prefix: String) {
        upBitRepository.getMarketPrice(prefix, this)
    }
}
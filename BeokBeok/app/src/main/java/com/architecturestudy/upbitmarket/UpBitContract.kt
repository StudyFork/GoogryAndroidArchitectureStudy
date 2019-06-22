package com.architecturestudy.upbitmarket

import com.architecturestudy.base.BasePresenter
import com.architecturestudy.base.BaseView
import com.architecturestudy.data.UpbitTicker

interface UpBitContract {
    interface View : BaseView<Presenter> {
        fun updateMarketPrice(marketPrice: List<UpbitTicker>)

        fun showErrMsg(err: String)
    }

    interface Presenter : BasePresenter {
        fun showMarketPrice(prefix: String)
    }
}
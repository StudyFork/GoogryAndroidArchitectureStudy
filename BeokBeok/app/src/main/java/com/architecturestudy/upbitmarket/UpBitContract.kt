package com.architecturestudy.upbitmarket

import com.architecturestudy.common.BasePresenter
import com.architecturestudy.common.BaseView
import com.architecturestudy.data.UpBitTicker

interface UpBitContract {
    interface View : BaseView<Presenter> {
        fun updateMarketPrice(marketPrice: List<UpBitTicker>)

        fun showErrMsg(err: String)
    }

    interface Presenter : BasePresenter {
        fun showMarketPrice(prefix: String)
    }
}
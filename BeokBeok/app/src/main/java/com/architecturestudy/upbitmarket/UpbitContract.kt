package com.architecturestudy.upbitmarket

import com.architecturestudy.base.BasePresenter
import com.architecturestudy.base.BaseView

interface UpbitContract {
    interface View : BaseView<Presenter> {
        fun updateMarketPrice(marketPrice: List<Map<String, String>>)

        fun showErrMsg(t: Throwable)
    }

    interface Presenter : BasePresenter {
        fun showMarketPrice(prefix: String)
    }
}
package study.architecture.myarchitecture.ui.tickerlist

import android.os.Bundle
import study.architecture.myarchitecture.data.model.UpbitTicker

interface TickerListContract {

    interface View {

        fun showProgress()

        fun hideProgress()

        fun getKeyMarkets(): String

        fun setTickers(tickers: MutableList<UpbitTicker>)

        fun orderByField(bundle: Bundle)
    }

    interface Presenter {

        fun createdView()

        fun detachView()

        fun loadData()
    }
}
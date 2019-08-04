package study.architecture.myarchitecture.ui.tickerlist

import android.os.Bundle
import study.architecture.myarchitecture.ui.model.TickerItem

interface TickerListContract {

    interface View {

        fun showProgress()

        fun hideProgress()

        fun getKeyMarkets(): String

        fun setTickers(tickers: MutableList<TickerItem>)

        fun orderByField(bundle: Bundle)
    }

    interface Presenter {

        fun createdView()

        fun detachView()

        fun loadData()
    }
}
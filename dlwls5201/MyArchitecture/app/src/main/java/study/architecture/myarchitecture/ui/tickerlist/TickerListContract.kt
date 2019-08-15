package study.architecture.myarchitecture.ui.tickerlist

import android.os.Bundle
import study.architecture.myarchitecture.ui.model.TickerItem

interface TickerListContract {

    interface View {

        fun showProgress()

        fun hideProgress()

        fun showTickers(tickers: MutableList<TickerItem>)

        fun orderByField(bundle: Bundle)
    }

    interface Presenter {

        fun createdView()

        fun detachView()

        fun loadData()
    }
}
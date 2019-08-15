package study.architecture.myarchitecture.ui.tickerlist

import study.architecture.myarchitecture.ui.model.TickerItem

interface TickerListContract {

    interface View {

        fun showProgress()

        fun hideProgress()

        fun showTickers(tickers: MutableList<TickerItem>)

        fun showTickerListOrderByField(field: String, order: Int)
    }

    interface Presenter {

        fun detachView()

        fun sortByField(tickers: MutableList<TickerItem>, field: String, order: Int)

        fun loadData()
    }
}
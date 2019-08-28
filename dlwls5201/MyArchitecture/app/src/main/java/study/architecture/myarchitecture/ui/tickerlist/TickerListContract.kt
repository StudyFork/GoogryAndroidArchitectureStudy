package study.architecture.myarchitecture.ui.tickerlist

import study.architecture.myarchitecture.ui.model.TickerItem
import study.architecture.myarchitecture.util.Filter

interface TickerListContract {

    interface View {

        fun showProgress()

        fun hideProgress()

        fun showTickers(tickers: MutableList<TickerItem>)

        //MainActivity 에서 사용
        fun showTickerListOrderByField(field: Filter.SelectArrow, order: Int)
    }

    interface Presenter {

        fun detachView()

        fun sortByField(field: Filter.SelectArrow, order: Int)

        fun loadData()
    }
}
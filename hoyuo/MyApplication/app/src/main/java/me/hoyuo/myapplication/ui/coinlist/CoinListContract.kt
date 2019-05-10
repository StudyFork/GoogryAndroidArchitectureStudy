package me.hoyuo.myapplication.ui.coinlist

import me.hoyuo.myapplication.model.upbit.Ticker
import me.hoyuo.myapplication.ui.base.BasePresenter
import me.hoyuo.myapplication.ui.base.BaseView

interface CoinListContract {
    interface View : BaseView<Presenter> {
        fun updateData(list: List<Ticker>)

        fun navigationCoinDetailActivity(ticker: Ticker)
    }

    interface Presenter : BasePresenter {
        fun onItemClick(it: Ticker)
    }

}

package com.namjackson.archstudy.view.coinlist

import com.namjackson.archstudy.base.BaseContract
import com.namjackson.archstudy.data.Ticker
import java.util.*

interface CoinListContract : BaseContract {

    interface View : BaseContract.View {
        fun showCoinList(ticker: List<Ticker>)
    }

    interface Presenter : BaseContract.Presenter {
        fun loadCoinList()

        fun changeBaseCurrency(baseCurrency: String)

        fun search(searchStr: String)
    }
}
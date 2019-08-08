package com.architecture.study.view.coin.presenter

import com.architecture.study.data.model.Ticker
import com.architecture.study.network.model.TickerResponse
import com.architecture.study.view.base.BasePresenter
import com.architecture.study.view.base.BaseView


interface CoinListFragmentContract {

    interface View : BaseView<Presenter> {
        var isActive: Boolean

        fun showTickerList(tickerList: List<Ticker>) // Ticker 리스트를 화면에 뿌려줌

        fun showEmptyTickerData(empty: String)

        fun showFailureGetTickerData(failure: String)

        fun successConnectApi() // Retrofit 객체 연결 성공

        fun showFailedConnectError() // Retrofit 객체 연결 실패
    }

    interface Presenter : BasePresenter{
        var isConnectApi: Boolean

        fun getTickerList(marketNames: String)
    }
}
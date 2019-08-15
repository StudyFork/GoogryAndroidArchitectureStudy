package com.architecture.study.view.coin.presenter

import com.architecture.study.network.model.MarketResponse
import com.architecture.study.view.base.BasePresenter
import com.architecture.study.view.base.BaseView

interface CoinListActivityContract {
    interface View : BaseView<Presenter> {
        fun setTabPager(marketList: List<MarketResponse>) // Fragment로 마켓리스트 보냄

        fun showEmptyMarketData(empty: String) //마켓 데이터 비어있음

        fun showFailureGetMarketData(failure: String) //마켓 데이터 로드 실패

        fun showFailedConnectError() // Retrofit 객체 연결 실패
    }

    interface Presenter : BasePresenter{
        var isConnectApi: Boolean

        fun getMarketList() // 마켓 목록 가져옴
    }
}
package my.gong.studygong.mvp.presenter

import my.gong.studygong.data.model.Ticker
import my.gong.studygong.mvp.base.BasePresenter
import my.gong.studygong.mvp.base.BaseView

interface CoinContract {

    interface Presenter: BasePresenter{
        fun populateCoinData()
    }

    interface View: BaseView<Presenter>{
        fun showTickers(ticker: List<Ticker>)
    }

}
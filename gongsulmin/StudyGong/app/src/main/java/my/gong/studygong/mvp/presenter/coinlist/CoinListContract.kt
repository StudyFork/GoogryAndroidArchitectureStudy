package my.gong.studygong.mvp.presenter.coinlist

import my.gong.studygong.data.model.Ticker

interface CoinListContract {

    interface Presenter {
        fun populateCoinData(coinMarket: String)
    }

    interface View {
        fun showTickers(ticker: List<Ticker>)
        fun showToast(msg: String)
    }

}
package my.gong.studygong.mvp.presenter.coinlist

import my.gong.studygong.data.model.Ticker

interface CoinListContract {

    interface Presenter {
        fun populateCoinData(coinMarket: String)
        fun searchCoin(coin: String)
    }

    interface View {
        fun showTickers(ticker: List<Ticker>)
        fun showSearchDialog(coin: String)
        fun showToast(msg: String)
    }

}
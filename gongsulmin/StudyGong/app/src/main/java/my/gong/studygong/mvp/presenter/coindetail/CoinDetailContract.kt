package my.gong.studygong.mvp.presenter.coinmarketlist

import my.gong.studygong.data.model.Ticker

interface CoinDetailContract {

    interface Presenter {
        fun populateCoinData(coinDetail: String)
    }

    interface View {
        fun showCoinMarket(coinDetail: List<Ticker>)
        fun errorCoinDetail(erroeMsg: String)
    }

}
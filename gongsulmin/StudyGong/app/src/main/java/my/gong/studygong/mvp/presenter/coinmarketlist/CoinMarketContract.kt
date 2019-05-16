package my.gong.studygong.mvp.presenter.coinmarketlist

interface CoinMarketContract {

    interface Presenter {
        fun populateCoinData()
    }

    interface View {
        fun showCoinMarket(coinMarkets: List<String>)
        fun showToast(msg: String)
    }

}
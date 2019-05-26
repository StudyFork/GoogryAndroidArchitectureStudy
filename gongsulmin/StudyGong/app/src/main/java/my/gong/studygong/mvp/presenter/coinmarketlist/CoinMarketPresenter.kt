package my.gong.studygong.mvp.presenter.coinmarketlist

import my.gong.studygong.data.source.upbit.UpbitDataSource

class CoinMarketPresenter(
    private val upbitRepository: UpbitDataSource,
    private val view: CoinMarketContract.View
) : CoinMarketContract.Presenter {

    override fun populateCoinData() {
        upbitRepository.getCoinCurrency(
            success = {
                view.showCoinMarket(it)
            },
            fail = {
                view.showToast(it)
            }
        )
    }
}


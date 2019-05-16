package my.gong.studygong.mvp.presenter.coinmarketlist

import my.gong.studygong.data.source.upbit.UpbitDataSource

class CoinDetailPresenter(
    private val upbitRepository: UpbitDataSource,
    private val view: CoinDetailContract.View
) : CoinDetailContract.Presenter {

    override fun populateCoinData(coinDetail: String) {
        upbitRepository.getDetailTickers(coinDetail,
            success = {
                view.showCoinMarket(it)
            },

            fail = {
                view.errorCoinDetail(it)
            }
        )
    }
}
package my.gong.studygong.mvp.presenter.coinlist

import my.gong.studygong.data.source.upbit.UpbitDataSource

class CoinListPresenter(
    private val coinRepository: UpbitDataSource,
    private val view: CoinListContract.View
) : CoinListContract.Presenter {
    override fun populateCoinData(coinMarket: String) {
        coinRepository.getTickers(
            coinMarket,
            success = {
                view.showTickers(it)
            },
            fail = {
                view.showToast(it)
            }
        )
    }
    override fun searchCoin(coin: String) {
        if (coin.isNotEmpty()){
            view.showSearchDialog(coin)
        }else{
            view.showToast(" 검색할 코인을 입력해주세요! ")
        }
    }
}
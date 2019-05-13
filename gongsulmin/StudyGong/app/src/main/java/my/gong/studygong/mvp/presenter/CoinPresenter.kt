package my.gong.studygong.mvp.presenter

import my.gong.studygong.data.model.enum.TickerCurrency
import my.gong.studygong.data.source.upbit.UpbitDataSource

class CoinPresenter(
    private val coinRepository: UpbitDataSource,
    private val view: CoinContract.View
) : CoinContract.Presenter {

    override fun onStart() {
        populateCoinData()
    }

    override fun populateCoinData() {
        coinRepository.getTickers(TickerCurrency.KRW,
            success = {
                view.showTickers(it)
            },
            fail = {
                view.showToast(it)
            }
        )
    }
}
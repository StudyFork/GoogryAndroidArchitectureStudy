package my.gong.studygong.mvp.presenter

import my.gong.studygong.data.model.enum.TickerCurrency
import my.gong.studygong.data.source.upbit.UpbitRepository

class CoinPresenter(
    val view: CoinContract.View
): CoinContract.Presenter {

    init {
        view.presenter = this
    }

    override fun onStart() {
        populateCoinData()
    }

    override fun populateCoinData() {
        UpbitRepository.getTickers(TickerCurrency.KRW ,
            success = {
                view.showTickers(it)
            } ,
            fail = {
                view.showToast(it)
            }
        )
    }
}
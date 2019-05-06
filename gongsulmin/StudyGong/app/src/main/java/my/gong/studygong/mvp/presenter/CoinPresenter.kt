package my.gong.studygong.mvp.presenter

import my.gong.studygong.data.model.enum.TickerCurrency
import my.gong.studygong.data.source.upbit.IUpbitDataSource

class CoinPresenter(
    val coinRepository: IUpbitDataSource ,
    val view: CoinContract.View
): CoinContract.Presenter {

    init {
        view.presenter = this
    }

    override fun onStart() {
        populateCoinData()
    }

    override fun populateCoinData() {
        coinRepository.getTickers(TickerCurrency.KRW ,
            success = {
                view.showTickers(it)
            } ,
            fail = {
                view.showToast(it)
            }
        )
    }
}
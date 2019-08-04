package study.architecture.myarchitecture.ui.tickerlist

import io.reactivex.disposables.CompositeDisposable
import study.architecture.myarchitecture.data.repository.UpbitRepository
import study.architecture.myarchitecture.rxobserver.RxObserverHelper
import study.architecture.myarchitecture.util.TextUtil
import timber.log.Timber

class TickerListPresenter(
    private val upbitRepository: UpbitRepository,
    private val view: TickerListContract.View,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : TickerListContract.Presenter {

    override fun createdView() {

        RxObserverHelper.tickerListSubject
            .subscribe {
                view.orderByField(it)
            }.also {
                compositeDisposable.add(it)
            }

        loadData()
    }

    override fun detachView() {
        compositeDisposable.clear()
    }

    override fun loadData() {
        val markets = view.getKeyMarkets()

        upbitRepository
            .getTickers(markets)
            .doOnSubscribe {
                view.showProgress()
            }
            .doOnSuccess {
                view.hideProgress()
            }
            .doOnError {
                view.hideProgress()
            }
            .subscribe({

                Timber.d("$it")

                it.map { ticker ->
                    ticker.setCoinName(TextUtil.getCoinName(ticker.market))
                    ticker.setLast(TextUtil.getLast(ticker.tradePrice))
                    ticker.setTradeDiff(TextUtil.getTradeDiff(ticker.signedChangeRate))
                    ticker.setTradeAmount(TextUtil.getTradeAmount(ticker.accTradePrice24h))
                }

                view.setTickers(it.toMutableList())

            }) {
                Timber.e(it)
            }.also {
                compositeDisposable.add(it)
            }
    }
}
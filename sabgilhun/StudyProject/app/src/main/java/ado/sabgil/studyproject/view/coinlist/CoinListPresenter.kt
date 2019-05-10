package ado.sabgil.studyproject.view.coinlist

import ado.sabgil.studyproject.data.remote.upbit.UpbitApiHandlerImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CoinListPresenter(
    private val baseCurrency: String,
    private val coinListView: CoinListContract.View
) : CoinListContract.Presenter {

    private val dataSource = UpbitApiHandlerImpl

    private val disposables = CompositeDisposable()

    init {
        coinListView.presenter = this
    }

    override fun subscribe() {
        disposables.add(dataSource
            .subscribeRemoteData()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { allTickers ->
                coinListView.updateCoinList(
                    allTickers.filter { it.base == baseCurrency }
                )
            })
    }

    override fun unSubscribe() {
        dataSource.unSubscribe()
        disposables.clear()
    }
}